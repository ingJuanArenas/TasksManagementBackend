package com.tasks.management.tasks_management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.management.tasks_management.mapper.TaskMapper;
import com.tasks.management.tasks_management.model.dto.request.TaskRequest;
import com.tasks.management.tasks_management.model.dto.response.TaskResponse;
import com.tasks.management.tasks_management.model.vo.Task;
import com.tasks.management.tasks_management.model.vo.Task.Status;
import com.tasks.management.tasks_management.repository.TaskRepository;
import com.tasks.management.tasks_management.service.TaskService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TaskMapper taskMapper;
    
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));
        return taskMapper.taskToTaskResponse(task);
    }
    
    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.taskRequestToTask(taskRequest);
        Task savedTask = taskRepository.save(task);
        return taskMapper.taskToTaskResponse(savedTask);
    }
    
    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));
        
        existingTask.setName(taskRequest.getName());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setDeadline(taskRequest.getDeadline());
        
        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.taskToTaskResponse(updatedTask);
    }
    
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarea no encontrada con ID: " + id);
        }
        taskRepository.deleteById(id);
    }
    
    @Override
    public TaskResponse updateTaskStatus(Long id, Status status) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada con ID: " + id));
        existingTask.setStatus(status);
        Task saved = taskRepository.save(existingTask);
        return taskMapper.taskToTaskResponse(saved);
    }
} 