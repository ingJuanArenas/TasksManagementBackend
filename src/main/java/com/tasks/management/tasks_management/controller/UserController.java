package com.tasks.management.tasks_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.tasks.management.tasks_management.model.vo.User;
import com.tasks.management.tasks_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Endpoint temporal para crear usuarios
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(role)); // Ejemplo: "ROLE_USER" o "ROLE_ADMIN"
        userRepository.save(user);
        return ResponseEntity.ok("Usuario creado correctamente");
    }
}
