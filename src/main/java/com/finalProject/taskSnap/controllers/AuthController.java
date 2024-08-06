package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.response.LoginResponse;
import com.finalProject.taskSnap.services.JwtService;
import com.finalProject.taskSnap.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/logout")
    public void customLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("Logging out");
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody TaskSnapUsers user) {
        TaskSnapUsers authenticateUser = userService.authenticateUser(user);
        String jwtToken = jwtService.generateToken(authenticateUser);
        LoginResponse response =  LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody TaskSnapUsers user) throws InstanceAlreadyExistsException {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

}