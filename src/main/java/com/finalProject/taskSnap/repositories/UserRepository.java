package com.finalProject.taskSnap.repositories;

import com.finalProject.taskSnap.models.TaskSnapUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TaskSnapUsers, Integer> {
    Optional<TaskSnapUsers> findTaskSnapUsersByUsername(String username);
}
