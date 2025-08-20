package com.tasks.management.tasks_management.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    
    @NotBlank(message = "El nombre de la tarea es obligatorio")
    @Size(max = 100, message = "El nombre de la tarea no puede exceder 100 caracteres")
    private String name;
    
    @NotBlank(message = "La descripción de la tarea es obligatoria")
    @Size(max = 500, message = "La descripción de la tarea no puede exceder 500 caracteres")
    private String description;
    
    @NotNull(message = "La fecha límite es obligatoria")
    private LocalDate deadline;
}
