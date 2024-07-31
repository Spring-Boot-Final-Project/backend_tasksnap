package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    public TaskSnapUsers getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(TaskSnapUsers user){
        if(userRepository.findTaskSnapUsersByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("Email is already registered");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
        } catch (Exception e){
            throw new IllegalStateException("Failed to save user: " + e);
        }

    }
}
