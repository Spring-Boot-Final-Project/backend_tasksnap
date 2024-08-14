package com.finalProject.taskSnap.repositories;

import com.finalProject.taskSnap.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByTaskSnapUserId(int id);

    // Custom JPQL query to find tasks due within 24 hours
    @Query("SELECT t FROM Tasks t WHERE (t.dueDate = :startDate AND t.dueTime >= :startTime) OR (t.dueDate = :endDate AND t.dueTime <= :endTime)")
    List<Tasks> findTasksDueWithin24Hours(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
