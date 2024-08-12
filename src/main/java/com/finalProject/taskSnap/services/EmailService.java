package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    // check log
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendTaskReminder(Tasks task){

        String recipientEmail = task.getTaskSnapUsers().getUsername();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Task Reminder");
        message.setText("Reminder: Your Task \"" + task.getTitle() + "\" is due in 24 hours.");

        message.setFrom("waitata2648@gmail.com"); // will sending from this account

        try {
            emailSender.send(message);
            logger.info("Email successfully sent to {}", recipientEmail);
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", recipientEmail, e.getMessage(), e);
        }
    }
}
