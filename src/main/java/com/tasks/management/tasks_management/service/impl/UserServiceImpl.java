package com.tasks.management.tasks_management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.management.tasks_management.mapper.UserMapper;
import com.tasks.management.tasks_management.model.dto.request.UserRequest;
import com.tasks.management.tasks_management.model.dto.response.UserResponse;
import com.tasks.management.tasks_management.model.vo.User;
import com.tasks.management.tasks_management.model.vo.Role;
import com.tasks.management.tasks_management.repository.UserRepository;
import com.tasks.management.tasks_management.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return userMapper.userToUserResponse(user);
    }
    
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe: " + userRequest.getUsername());
        }
        
        User user = userMapper.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserResponse(savedUser);
    }
    
    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        
        // Verificar si el nuevo username ya existe en otro usuario
        if (!existingUser.getUsername().equals(userRequest.getUsername()) && 
            userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe: " + userRequest.getUsername());
        }
        
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(userRequest.getPassword());
        // Actualizar rol si viene en la solicitud
        if (userRequest.getRole() != null) {
            existingUser.setRole(userRequest.getRole());
        }
        
        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserResponse(updatedUser);
    }
    
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }
} 