package com.perfuland.perfulandia.service.impl;

import java.util.List;

import com.perfuland.perfulandia.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.service.UserService;

@Service
public class UsersServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id); // Reutilizamos el método para encontrar o lanzar excepción

        user.setUserName(userDetails.getUserName());
        user.setAddress(userDetails.getAddress());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        // Opcional: verificar si el usuario existe antes de borrarlo
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
}
