package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){

        this.taskRepository = taskRepository;
    }

    // Save tasks
    public void saveTask(Tasks task){
        Tasks existingTask = taskRepository.findById(task.getId()).orElse(null);
        if(existingTask != null) throw new IllegalStateException("Task with ID " + task.getId() + " already exists");
        taskRepository.save(task);
    }

    // List All tasks
    public List<Tasks> getAllTask(){
        return taskRepository.findAll();
    }

    // List task by id
    public Optional<Tasks> getTaskById(int id){
        return taskRepository.findById(id);
    }

    // Update tasks
    public void updateTask(Tasks task) {
        Optional<Tasks> existingTask = taskRepository.findById(task.getId());
        if (existingTask.isPresent()) {
            Tasks managedTask = existingTask.get();
            managedTask.setTitle(task.getTitle());
            managedTask.setStatus(task.getStatus());
            managedTask.setStartDate(task.getStartDate());
            managedTask.setStartTime(task.getStartTime());
            managedTask.setDueDate(task.getDueDate());
            managedTask.setDueTime(task.getDueTime());
            managedTask.setAllDay(task.getAllDay());
            managedTask.setLabel(task.getLabel());
            managedTask.setDescription(task.getDescription());
            managedTask.setTaskSnapUsers(task.getTaskSnapUsers());
            taskRepository.save(managedTask);
        } else {
            throw new IllegalStateException("Task with ID " + task.getId() + " doesn't exist");
        }
    }

    // Delete tasks
    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

}
