package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

//    Get the user by id
    public TaskSnapUsers getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

//    Get the user by username
    public TaskSnapUsers getUserByUsername(String username) {
        return userRepository.findTaskSnapUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    Save the user, if the user already exists, throw an exception
    public void saveUser(TaskSnapUsers user) throws InstanceAlreadyExistsException {
        if (userRepository.findTaskSnapUsersByUsername(user.getUsername()).isPresent()) {
            throw new InstanceAlreadyExistsException("Email is already registered");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save user: " + e);
        }
    }

//    Authenticate the user
    public TaskSnapUsers authenticateUser(TaskSnapUsers user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return userRepository.findTaskSnapUsersByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
