package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.services.TaskService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
}
