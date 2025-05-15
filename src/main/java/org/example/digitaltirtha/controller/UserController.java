package org.example.digitaltirtha.controller;

import org.example.digitaltirtha.model.User;
import org.example.digitaltirtha.service.UserService;
import org.example.digitaltirtha.dao.UserDAO;
import org.example.digitaltirtha.dao.impl.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Controller for handling user-related requests following MVC pattern.
 */
@WebServlet(urlPatterns = {"/users/*", "/login", "/signup", "/logout", "/profile"})
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
        // Sample data initialization is now handled by DatabaseInitializer
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                // Show login page
                request.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(request, response);
                break;

            case "/signup":
                // Show signup page
                request.getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
                break;

            case "/logout":
                // Handle logout
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/");
                break;

            case "/profile":
                // Show user profile page
                HttpSession userSession = request.getSession(false);
                if (userSession != null && userSession.getAttribute("user") != null) {
                    User user = (User) userSession.getAttribute("user");
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/views/users/profile.jsp").forward(request, response);
                } else {
                    // Redirect to login if not logged in
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            default:
                // Handle /users/* paths
                if (path.startsWith("/users")) {
                    String pathInfo = request.getPathInfo();
                    if (pathInfo != null && pathInfo.length() > 1) {
                        try {
                            Long userId = Long.parseLong(pathInfo.substring(1));
                            User user = userService.getUserById(userId);
                            if (user != null) {
                                request.setAttribute("user", user);
                                request.getRequestDispatcher("/WEB-INF/views/users/detail.jsp").forward(request, response);
                                return;
                            }
                        } catch (NumberFormatException e) {
                            // Invalid ID format
                        }
                    }
                }
                // If we get here, it's an invalid path
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                // Handle login form submission
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (username != null && password != null) {
                    User user = userService.authenticateUser(username, password);
                    if (user != null) {
                        // Authentication successful
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("userId", user.getId());
                        session.setAttribute("username", user.getUsername());
                        session.setAttribute("isLoggedIn", true);

                        // Update last login date
                        user.setLastLoginDate(new Date());
                        userService.updateUser(user);

                        // Redirect to home page or requested page
                        String redirectUrl = (String) session.getAttribute("redirectUrl");
                        if (redirectUrl != null) {
                            session.removeAttribute("redirectUrl");
                            response.sendRedirect(redirectUrl);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/");
                        }
                    } else {
                        // Authentication failed
                        request.setAttribute("error", "Invalid username or password");
                        request.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(request, response);
                    }
                } else {
                    // Missing parameters
                    request.setAttribute("error", "Username and password are required");
                    request.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(request, response);
                }
                break;

            case "/signup":
                // Handle signup form submission
                String newUsername = request.getParameter("username");
                String newPassword = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String email = request.getParameter("email");
                String fullName = request.getParameter("fullName");
                String country = request.getParameter("country");

                // Validate input
                if (newUsername == null || newPassword == null || confirmPassword == null || 
                    email == null || fullName == null) {
                    request.setAttribute("error", "All fields are required");
                    request.getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("error", "Passwords do not match");
                    request.getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
                    return;
                }

                // Check if username already exists
                if (userService.getUserByUsername(newUsername) != null) {
                    request.setAttribute("error", "Username already exists");
                    request.getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
                    return;
                }

                // Create new user
                User newUser = new User();
                newUser.setUsername(newUsername);
                newUser.setPassword(newPassword); // In a real app, this would be hashed
                newUser.setEmail(email);
                newUser.setFullName(fullName);
                newUser.setCountry(country);
                newUser.setLocal(country != null && country.equalsIgnoreCase("Nepal"));

                User registeredUser = userService.registerUser(newUser);

                if (registeredUser != null) {
                    // Registration successful
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", registeredUser);
                    session.setAttribute("userId", registeredUser.getId());
                    session.setAttribute("username", registeredUser.getUsername());
                    session.setAttribute("isLoggedIn", true);

                    response.sendRedirect(request.getContextPath() + "/");
                } else {
                    // Registration failed
                    request.setAttribute("error", "Registration failed");
                    request.getRequestDispatcher("/WEB-INF/views/users/signup.jsp").forward(request, response);
                }
                break;

            case "/profile":
                // Handle profile update
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("user") != null) {
                    User currentUser = (User) session.getAttribute("user");

                    // Update user information
                    String updatedEmail = request.getParameter("email");
                    String updatedFullName = request.getParameter("fullName");
                    String updatedCountry = request.getParameter("country");

                    if (updatedEmail != null && updatedFullName != null) {
                        currentUser.setEmail(updatedEmail);
                        currentUser.setFullName(updatedFullName);
                        if (updatedCountry != null) {
                            currentUser.setCountry(updatedCountry);
                            currentUser.setLocal(updatedCountry.equalsIgnoreCase("Nepal"));
                        }

                        User updatedUser = userService.updateUser(currentUser);
                        if (updatedUser != null) {
                            session.setAttribute("user", updatedUser);
                            request.setAttribute("success", "Profile updated successfully");
                        } else {
                            request.setAttribute("error", "Failed to update profile");
                        }
                    }

                    request.setAttribute("user", currentUser);
                    request.getRequestDispatcher("/WEB-INF/views/users/profile.jsp").forward(request, response);
                } else {
                    // Redirect to login if not logged in
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
