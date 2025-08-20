package com.tasks.management.tasks_management.mapper;

import org.springframework.stereotype.Component;

import com.tasks.management.tasks_management.model.dto.request.UserRequest;
import com.tasks.management.tasks_management.model.dto.response.UserResponse;
import com.tasks.management.tasks_management.model.vo.User;
import com.tasks.management.tasks_management.model.vo.Role;

@Component
public class UserMapper {
    
    public User userRequestToUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole() != null ? userRequest.getRole() : Role.USER);
        
        return user;
    }
    
    public UserResponse userToUserResponse(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        
        return response;
    }
} 