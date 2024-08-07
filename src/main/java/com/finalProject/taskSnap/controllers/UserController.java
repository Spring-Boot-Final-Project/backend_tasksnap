package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    Get the detail of the current user who is logged in
    @GetMapping
    public ResponseEntity<TaskSnapUsers> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaskSnapUsers currentUser = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(currentUser);
    }
}
