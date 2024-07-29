package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login?message=You have been logged out successfully!";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("user", new TaskSnapUsers());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute TaskSnapUsers user) {
        int saveUserCode = userService.saveUser(user);
        if (saveUserCode == 0) {
            return "redirect:/register?message=Username already exists!";
        } else {
            return "redirect:/login?message=Registration successful!";
        }
    }
}