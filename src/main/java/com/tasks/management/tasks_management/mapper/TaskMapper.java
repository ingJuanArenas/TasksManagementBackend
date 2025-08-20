package com.tasks.management.tasks_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tasks.management.tasks_management.model.dto.request.TaskRequest;
import com.tasks.management.tasks_management.model.dto.response.TaskResponse;
import com.tasks.management.tasks_management.model.vo.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "tasksTList", ignore = true)
    Task taskRequestToTask(TaskRequest taskRequest);
    
    TaskResponse taskToTaskResponse(Task task);
} 