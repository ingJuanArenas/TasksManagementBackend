package com.tasks.management.tasks_management.mapper;

import org.springframework.stereotype.Component;

import com.tasks.management.tasks_management.model.dto.request.TaskRequest;
import com.tasks.management.tasks_management.model.dto.response.TaskResponse;
import com.tasks.management.tasks_management.model.vo.Task;

@Component
public class TaskMapper {
    
    public Task taskRequestToTask(TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }
        
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setDeadline(taskRequest.getDeadline());
        task.setStatus(Task.Status.PENDING);
        
        return task;
    }
    
    public TaskResponse taskToTaskResponse(Task task) {
        if (task == null) {
            return null;
        }
        
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setDescription(task.getDescription());
        response.setDeadline(task.getDeadline());
        response.setStatus(task.getStatus());
        
        return response;
    }
} 