package com.finalProject.taskSnap.services;

import com.finalProject.taskSnap.models.Tasks;
import com.finalProject.taskSnap.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

//    Hide details of users when returning tasks
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

    // Manually send email to all tasks, only for send email.
//    public List<Tasks> sendEmailToAllTask() {
//        return taskRepository.findAll();
//    }

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

    // Common method to find and send emails for tasks due within 24 hours
    public void sendEmailForTasksDueWithin24Hours(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in24Hours = now.plusHours(24);

        LocalDate dueDate = in24Hours.toLocalDate();
        LocalTime dueTime = in24Hours.toLocalTime();

        // Find all tasks due within the next 24 hours
        List<Tasks> tasksToNotify = taskRepository.findTasksDueWithin24Hours(now.toLocalDate(), dueDate, now.toLocalTime(), dueTime);

        for (Tasks task : tasksToNotify) {
            emailService.sendTaskReminder(task);
        }
    }

    // Notification of mailing

    // Scheduled method to run every 12 hours
    @Scheduled(cron = "0 0 0/12 * * ?")
    public void checkTasksAndSendEmail() {
        sendEmailForTasksDueWithin24Hours();
    }

    // Method to be called manually
    public List<Tasks> sendEmailToAllTask() {
        sendEmailForTasksDueWithin24Hours();
        return taskRepository.findAll();
    }


    // Notification of mailing
//    @Scheduled(cron = "0 0 0/12 * * ?") // Runs every 12 hours
//    public void checkTasksAndSendEmail() {
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        LocalTime startTime = LocalTime.of(0, 0); // 00:00
//
//        // Find all tasks due tomorrow at 00:00
//        List<Tasks> tasksToNotify = taskRepository.findByDueDateAndDueTime(tomorrow, startTime);
//
//        for (Tasks task : tasksToNotify) {
//            emailService.sendTaskReminder(task);
//        }
//    }


}
