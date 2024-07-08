package com.finalProject.taskSnap.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserService userService;

    public UserService(UserService userService){
        this.userService = userService;
    }
}
