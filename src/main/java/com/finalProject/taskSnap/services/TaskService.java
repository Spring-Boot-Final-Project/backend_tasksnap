package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public int saveTask(Tasks task){
        taskRepository.save(task);
        return 1;
    }
}
