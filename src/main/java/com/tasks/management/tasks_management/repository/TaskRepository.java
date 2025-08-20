package com.tasks.management.tasks_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.management.tasks_management.model.vo.Task;
import com.tasks.management.tasks_management.model.vo.Task.Status;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByStatus(Status status);
    
    List<Task> findByNameContainingIgnoreCase(String name);
} 