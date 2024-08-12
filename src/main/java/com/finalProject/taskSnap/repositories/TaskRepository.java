package com.finalProject.taskSnap.repositories;

import com.finalProject.taskSnap.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByTaskSnapUserId(int id);

    // query upcoming due tasks by date and time
    List<Tasks> findByDueDateAndDueTime(LocalDate dueDate, LocalTime dueTime);
}
