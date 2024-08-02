package com.finalProject.taskSnap.controllers;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.services.TaskService;
import com.finalProject.taskSnap.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// CrossOrigin to allow the frontend to access the backend
@CrossOrigin
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
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
    public ResponseEntity<String> createTask(@RequestBody Tasks task){
        TaskSnapUsers existedUser = userService.getUserById(task.getTaskSnapUserId());
        if(existedUser != null){
//            Assign the TaskSnapUsers object to the task
            task.setTaskSnapUsers(existedUser);
        }
        try{
            taskService.saveTask(task);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Task created successfully");
    }

    // Update task
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Tasks task){
        TaskSnapUsers existedUser = userService.getUserById(task.getTaskSnapUserId());
        if(existedUser != null){
//            Assign the TaskSnapUsers object to the task
            task.setTaskSnapUsers(existedUser);
        }
        task.setId(id);
        try{
            taskService.updateTask(task);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Task updated successfully");
    }

    // Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }


}
