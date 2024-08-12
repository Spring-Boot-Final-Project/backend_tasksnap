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
    private EmailService emailService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/testSendEmails")
    public String taskSendEmails() {

        try {
            // Fetch all tasks from the database
            List<Tasks> allTasks = taskService.sendEmailToAllTask();

            // Iterate through each task and send an email
            for (Tasks task : allTasks) {
                emailService.sendTaskReminder(task);
            }
            return "Emails sent successfully!";
        } catch (Exception e) {
            return "Emails sent failed! Error: " + e.getMessage();
        }
    }
}
