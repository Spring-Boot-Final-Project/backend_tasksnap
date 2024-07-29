package com.finalProject.taskSnap.repositories;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TaskSnapUsers, Long> {
    Optional<TaskSnapUsers> findByName(String name);
}
