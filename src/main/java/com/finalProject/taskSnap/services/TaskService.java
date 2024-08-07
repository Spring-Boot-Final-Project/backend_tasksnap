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

    public List<Tasks> hideUsers(List<Tasks> tasks){
        for(Tasks task: tasks){
            task.setTaskSnapUsers(null);
        }
        return tasks;
    }

    // Save tasks
    public void saveTask(Tasks task){
        taskRepository.save(task);
    }

    // List All tasks
    public List<Tasks> getAllTask(int id){
        return hideUsers(taskRepository.findByTaskSnapUserId(id));
    }

    // List task by id
    public Optional<Tasks> getTaskById(int id){
        return taskRepository.findById(id);
    }

    // Update tasks
    public void updateTask(Tasks task) {
        Optional<Tasks> existingTask = taskRepository.findById(task.getId());
        if (existingTask.isPresent()) {
//            Create a managed task which refers to the existing task
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
