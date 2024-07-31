package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
    public void customLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody TaskSnapUsers user) {
        try{
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
         return ResponseEntity.ok("User registered successfully");
    }
}