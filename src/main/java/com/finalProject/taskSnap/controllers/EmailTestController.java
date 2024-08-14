package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.services.EmailService;
import com.finalProject.taskSnap.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finalProject.taskSnap.models.Tasks;

import java.util.List;

// this class is only for test sending email MANUALLY

@RestController
public class EmailTestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/testSendEmails")
    public String taskSendEmails() {
        try {
            taskService.sendEmailForTasksDueWithin24Hours();
            return "Emails sent successfully!";
        } catch (Exception e) {
            return "Emails sent failed! Error: " + e.getMessage();
        }
    }
}
