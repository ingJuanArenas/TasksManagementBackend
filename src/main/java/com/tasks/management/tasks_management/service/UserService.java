package com.tasks.management.tasks_management.service;

import java.util.List;

import com.tasks.management.tasks_management.model.dto.request.UserRequest;
import com.tasks.management.tasks_management.model.dto.response.UserResponse;
import com.tasks.management.tasks_management.model.vo.Role;

public interface UserService {
    
    List<UserResponse> getAllUsers();
    
    UserResponse getUserById(Long id);
    
    UserResponse createUser(UserRequest userRequest);
    
    UserResponse updateUser(Long id, UserRequest userRequest);
    
    void deleteUser(Long id);

    List<UserResponse> getUsersByRole(Role role);
} 