package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Tasks> homepage(){

        return taskService.getAllTask();
    }

    // List task by id
    @GetMapping("/{id}")
    public Optional<Tasks> getTaskById(@PathVariable int id){
        return taskService.getTaskById(id);
    }

    // Create task
    @PostMapping("/create")
    public int createTask(@RequestBody Tasks task){
        return taskService.saveTask(task);
    }

    // Update task
    @PutMapping("/update/{id}")
    public int updateTask(@PathVariable int id, @RequestBody Tasks task){
        task.setId(id);
        return taskService.updateTask(task);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }


}
