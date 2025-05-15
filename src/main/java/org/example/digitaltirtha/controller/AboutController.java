package org.example.digitaltirtha.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for handling about page requests following MVC pattern.
 */
@WebServlet(urlPatterns = {"/about"})
public class AboutController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the about page
        request.getRequestDispatcher("/WEB-INF/views/about.jsp").forward(request, response);
    }
}