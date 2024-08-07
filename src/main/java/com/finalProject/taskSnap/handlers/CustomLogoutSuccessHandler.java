package com.finalProject.taskSnap.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * Handles the logout success event.
     *
     * This method is called when a user successfully logs out. It sets the HTTP response status to OK (200)
     * and flushes the response writer.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param authentication the authentication object (can be null if the user was not authenticated)
     * @throws IOException if an input or output exception occurs
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
}

