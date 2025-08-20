package com.tasks.management.tasks_management.service;

import java.util.List;

import com.tasks.management.tasks_management.model.dto.request.TaskRequest;
import com.tasks.management.tasks_management.model.dto.response.TaskResponse;
import com.tasks.management.tasks_management.model.vo.Task.Status;

public interface TaskService {
    
    List<TaskResponse> getAllTasks();
    
    TaskResponse getTaskById(Long id);
    
    TaskResponse createTask(TaskRequest taskRequest);
    
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    
    void deleteTask(Long id);
    
    List<TaskResponse> getTasksByStatus(Status status);
    
    List<TaskResponse> getTasksByName(String name);

    TaskResponse updateTaskStatus(Long id, Status status);
} 