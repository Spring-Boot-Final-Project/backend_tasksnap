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
    public int saveTask(Tasks task){
        taskRepository.save(task);
        return 1;
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
    public int updateTask(Tasks task){
        if(taskRepository.existsById(task.getId())){
            taskRepository.save(task);
            return 1;
        } else {
            return 0;
        }

    }

    // Delete tasks
    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }
}
