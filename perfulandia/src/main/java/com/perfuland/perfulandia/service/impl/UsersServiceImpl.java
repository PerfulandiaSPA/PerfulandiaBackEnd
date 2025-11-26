package com.perfuland.perfulandia.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.perfuland.perfulandia.model.User;
import com.perfuland.perfulandia.repository.UserRepository;
import com.perfuland.perfulandia.service.UsersService; // <-- Importar interfaz

@Service
public class UsersServiceImpl implements UsersService { // <-- Implementa la interfaz

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long idUser) { // <-- Corrección de firma a idUser
        return userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + idUser));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long idUser, User user) { // <-- Corrección de firma a idUser
        User existingUser = getUserById(idUser);
        existingUser.setUserName(user.getUserName()); // <-- Usa setUserName (corregido)
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setUserType(user.getUserType()); // <-- Usa setUserType (corregido)
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long idUser) { // <-- Corrección de firma a idUser
        userRepository.deleteById(idUser);
    }

    // Lógica de Negocio

    @Override
    public User loginUser(String userName, String password) { // <-- Corregido userName
        List<User> users = userRepository.findByUserName(userName);
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (user.checkPassword(password)) {
                return user;
            }
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public User RegisterUser(String userName, String email, String password) { // <-- Corregido userName
        User newUser = new User();
        newUser.setUserName(userName); // <-- Usa setUserName (corregido)
        newUser.setEmail(email);
        newUser.setPlainPassword(password); // Método que genera salt y hash
        // Asume un userType por defecto si no se pasa.
        // newUser.setUserType("customer");
        return userRepository.save(newUser);
    }

    @Override
    public User updatePassword(Long idUser, String newPassword) { // <-- Corregido firma
        User existingUser = getUserById(idUser);
        existingUser.setPlainPassword(newPassword); // Reusa el setter de la entidad
        return userRepository.save(existingUser);
    }
}