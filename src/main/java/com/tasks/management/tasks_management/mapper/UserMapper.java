package com.tasks.management.tasks_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tasks.management.tasks_management.model.dto.request.UserRequest;
import com.tasks.management.tasks_management.model.dto.response.UserResponse;
import com.tasks.management.tasks_management.model.vo.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasksTList", ignore = true)
    @Mapping(target = "role", constant = "USER")
    User userRequestToUser(UserRequest userRequest);
    
    UserResponse userToUserResponse(User user);
} 