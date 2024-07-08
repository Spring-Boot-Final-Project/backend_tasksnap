package com.finalProject.taskSnap.repositories;

import com.finalProject.taskSnap.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, String> {
}
