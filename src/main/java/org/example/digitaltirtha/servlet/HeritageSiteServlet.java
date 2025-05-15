package org.example.digitaltirtha.servlet;

import org.example.digitaltirtha.model.HeritageSite;
import org.example.digitaltirtha.service.HeritageSiteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling heritage site requests.
 * @deprecated Use SiteController instead
 */
public class HeritageSiteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HeritageSiteService siteService;

    @Override
    public void init() throws ServletException {
        super.init();
        siteService = new HeritageSiteService();
        siteService.initWithSampleData();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Get all sites
            List<HeritageSite> sites = siteService.getAllSites();
            request.setAttribute("sites", sites);
            request.getRequestDispatcher("/WEB-INF/views/sites/list.jsp").forward(request, response);
        } else if (pathInfo.startsWith("/risk/")) {
            // Get sites by risk level
            String riskLevel = pathInfo.substring(6);
            List<HeritageSite> sites = siteService.getSitesByRiskLevel(riskLevel);
            request.setAttribute("sites", sites);
            request.setAttribute("filterType", "Risk Level");
            request.setAttribute("filterValue", riskLevel);
            request.getRequestDispatcher("/WEB-INF/views/sites/list.jsp").forward(request, response);
        } else if (pathInfo.startsWith("/category/")) {
            // Get sites by category
            String category = pathInfo.substring(10);
            List<HeritageSite> sites = siteService.getSitesByCategory(category);
            request.setAttribute("sites", sites);
            request.setAttribute("filterType", "Category");
            request.setAttribute("filterValue", category);
            request.getRequestDispatcher("/WEB-INF/views/sites/list.jsp").forward(request, response);
        } else {
            try {
                // Get site by ID
                Long id = Long.parseLong(pathInfo.substring(1));
                HeritageSite site = siteService.getSiteById(id);

                if (site != null) {
                    request.setAttribute("site", site);
                    request.getRequestDispatcher("/WEB-INF/views/sites/detail.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Heritage site not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid site ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Create new site
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String category = request.getParameter("category");
            String riskLevel = request.getParameter("riskLevel");

            if (name != null && description != null && location != null) {
                HeritageSite site = new HeritageSite(null, name, description, location);
                site.setCategory(category);
                site.setRiskLevel(riskLevel);

                HeritageSite createdSite = siteService.createSite(site);
                response.sendRedirect(request.getContextPath() + "/sites/" + createdSite.getId());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            }
        } else if (pathInfo.contains("/visit")) {
            // Record visit
            try {
                String idStr = pathInfo.substring(1, pathInfo.indexOf("/visit"));
                Long id = Long.parseLong(idStr);

                HeritageSite site = siteService.recordVisit(id);
                if (site != null) {
                    response.sendRedirect(request.getContextPath() + "/sites/" + id);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Heritage site not found");
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid site ID");
            }
        } else if (pathInfo.contains("/donate")) {
            // Record donation
            try {
                String idStr = pathInfo.substring(1, pathInfo.indexOf("/donate"));
                Long id = Long.parseLong(idStr);

                String amountStr = request.getParameter("amount");
                if (amountStr != null && !amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);

                    HeritageSite site = siteService.recordDonation(id, amount);
                    if (site != null) {
                        response.sendRedirect(request.getContextPath() + "/sites/" + id);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Heritage site not found");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing amount parameter");
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid site ID or amount");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            try {
                Long id = Long.parseLong(pathInfo.substring(1));

                // Read request parameters
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String location = request.getParameter("location");
                String category = request.getParameter("category");
                String riskLevel = request.getParameter("riskLevel");

                // Get existing site
                HeritageSite site = siteService.getSiteById(id);

                if (site != null) {
                    // Update site properties
                    if (name != null) site.setName(name);
                    if (description != null) site.setDescription(description);
                    if (location != null) site.setLocation(location);
                    if (category != null) site.setCategory(category);
                    if (riskLevel != null) site.setRiskLevel(riskLevel);

                    // Update site
                    HeritageSite updatedSite = siteService.updateSite(site);

                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Heritage site not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid site ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            try {
                Long id = Long.parseLong(pathInfo.substring(1));

                boolean deleted = siteService.deleteSite(id);

                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Heritage site not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid site ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
        }
    }
}
