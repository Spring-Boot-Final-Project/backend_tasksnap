package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public TaskSnapUsers getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
}
