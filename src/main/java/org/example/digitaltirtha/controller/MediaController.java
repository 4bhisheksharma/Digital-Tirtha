package org.example.digitaltirtha.controller;

import org.example.digitaltirtha.model.MediaContribution;
import org.example.digitaltirtha.model.User;
import org.example.digitaltirtha.service.HeritageSiteService;
import org.example.digitaltirtha.service.UserService;
import org.example.digitaltirtha.dao.MediaContributionDAO;
import org.example.digitaltirtha.dao.impl.MediaContributionDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controller for handling media contribution-related requests following MVC pattern.
 */
@WebServlet(urlPatterns = {"/media/*", "/media/upload", "/media/view/*"})
@MultipartConfig
public class MediaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "uploads";

    private MediaContributionDAO mediaContributionDAO;
    private HeritageSiteService siteService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        mediaContributionDAO = new MediaContributionDAOImpl();
        siteService = new HeritageSiteService();
        userService = new UserService();
        // Sample data initialization is now handled by DatabaseInitializer

        // Create upload directory if it doesn't exist
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Save the requested URL for redirect after login
            session = request.getSession(true);
            session.setAttribute("redirectUrl", request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if ("/upload".equals(pathInfo)) {
            // Show upload form
            Long siteId = null;
            try {
                siteId = Long.parseLong(request.getParameter("siteId"));
                request.setAttribute("site", siteService.getSiteById(siteId));
            } catch (NumberFormatException | NullPointerException e) {
                // Invalid or missing site ID
                response.sendRedirect(request.getContextPath() + "/sites");
                return;
            }

            request.getRequestDispatcher("/WEB-INF/views/media/upload.jsp").forward(request, response);
        } else if (pathInfo != null && pathInfo.startsWith("/view/")) {
            // View a specific media contribution
            try {
                Long mediaId = Long.parseLong(pathInfo.substring(6));
                MediaContribution media = mediaContributionDAO.findById(mediaId);

                if (media != null) {
                    request.setAttribute("media", media);
                    request.setAttribute("site", siteService.getSiteById(media.getSiteId()));
                    request.setAttribute("user", userService.getUserById(media.getUserId()));

                    // Increment view count
                    media.incrementViewCount();
                    mediaContributionDAO.update(media);

                    request.getRequestDispatcher("/WEB-INF/views/media/view.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // Invalid ID format
            }

            // Media not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            // List all media contributions
            List<MediaContribution> mediaList = mediaContributionDAO.findAll();
            request.setAttribute("mediaList", mediaList);
            request.getRequestDispatcher("/WEB-INF/views/media/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        if ("/upload".equals(pathInfo)) {
            // Handle media upload
            Long siteId = null;
            try {
                siteId = Long.parseLong(request.getParameter("siteId"));
            } catch (NumberFormatException e) {
                // Invalid site ID
                response.sendRedirect(request.getContextPath() + "/sites");
                return;
            }

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String mediaType = request.getParameter("mediaType");
            String location = request.getParameter("location");

            // Validate input
            if (title == null || title.trim().isEmpty() || 
                description == null || description.trim().isEmpty() ||
                mediaType == null || mediaType.trim().isEmpty()) {

                request.setAttribute("error", "All fields are required");
                request.setAttribute("site", siteService.getSiteById(siteId));
                request.getRequestDispatcher("/WEB-INF/views/media/upload.jsp").forward(request, response);
                return;
            }

            // Handle file upload
            Part filePart = request.getPart("file");
            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("error", "Please select a file to upload");
                request.setAttribute("site", siteService.getSiteById(siteId));
                request.getRequestDispatcher("/WEB-INF/views/media/upload.jsp").forward(request, response);
                return;
            }

            // Save the file
            String fileName = UUID.randomUUID().toString() + getFileExtension(filePart);
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

            try (InputStream input = filePart.getInputStream()) {
                Path filePath = Paths.get(uploadPath, fileName);
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);

                // Create media contribution
                MediaContribution media = new MediaContribution(user.getId(), siteId, mediaType, 
                        UPLOAD_DIRECTORY + File.separator + fileName);
                media.setTitle(title);
                media.setDescription(description);
                media.setLocation(location);
                media.setUploadDate(new Date());
                media.setCaptureDate(new Date()); // In a real app, this would be parsed from the form

                // Save to database
                MediaContribution createdMedia = mediaContributionDAO.create(media);

                // Update user's media contribution count
                user.incrementMediaContributed();
                userService.updateUser(user);

                // Redirect to view the uploaded media
                response.sendRedirect(request.getContextPath() + "/media/view/" + createdMedia.getId());
            } catch (IOException e) {
                request.setAttribute("error", "Failed to upload file: " + e.getMessage());
                request.setAttribute("site", siteService.getSiteById(siteId));
                request.getRequestDispatcher("/WEB-INF/views/media/upload.jsp").forward(request, response);
            }
        } else if (pathInfo != null && pathInfo.startsWith("/like/")) {
            // Handle like action
            try {
                Long mediaId = Long.parseLong(pathInfo.substring(6));
                MediaContribution media = mediaContributionDAO.findById(mediaId);

                if (media != null) {
                    media.incrementLikeCount();
                    mediaContributionDAO.update(media);
                    response.sendRedirect(request.getContextPath() + "/media/view/" + mediaId);
                    return;
                }
            } catch (NumberFormatException e) {
                // Invalid ID format
            }

            // Media not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Gets the file extension from a Part
     * 
     * @param part The Part containing the file
     * @return The file extension (including the dot)
     */
    private String getFileExtension(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                String fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
                int dotIndex = fileName.lastIndexOf(".");
                if (dotIndex > 0) {
                    return fileName.substring(dotIndex);
                }
            }
        }

        return "";
    }
}
