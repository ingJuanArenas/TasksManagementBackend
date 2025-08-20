package com.tasks.management.tasks_management.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.tasks.management.tasks_management.model.vo.Task.Status;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Status status;
} 