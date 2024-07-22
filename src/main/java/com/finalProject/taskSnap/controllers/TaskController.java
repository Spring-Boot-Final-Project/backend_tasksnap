package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Tasks> homepage(){
        return taskService.getAllTask();
    }


}
