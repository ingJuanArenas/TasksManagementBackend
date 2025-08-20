package com.tasks.management.tasks_management.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.management.tasks_management.model.dto.request.TaskRequest;
import com.tasks.management.tasks_management.model.dto.response.TaskResponse;
import com.tasks.management.tasks_management.model.vo.Task.Status;
import com.tasks.management.tasks_management.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getTasksByStatus(@PathVariable Status status) {
        return taskService.getTasksByStatus(status);
    }
    
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getTasksByName(@RequestParam String name) {
        return taskService.getTasksByName(name);
    }

    @PutMapping("/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTaskStatus(@PathVariable Long id, @PathVariable Status status) {
        return taskService.updateTaskStatus(id, status);
    }
} 