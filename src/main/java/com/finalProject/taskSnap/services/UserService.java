package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //method to save the user information to the database(register)
    //0=> already exists 1=>successfully saved
    public int saveUser (TaskSnapUsers user){
        //check if the username already exists in the db
        if(userRepository.findByName(user.getName()).isPresent()){
            return 0;
        }

        //encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save the user information to the db
        userRepository.save(user);
        return 1;
    }
}