package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.services.UserService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements ErrorController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }
}
