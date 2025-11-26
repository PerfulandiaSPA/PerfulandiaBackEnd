package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.User;

public interface UsersService {
    // Deberia haber un servicio para usuarios, no para perfumes en este archivo

    List<User> getAllUsers();

    User getUserById(Long id_user);

    User createUser(User user);

    User updateUser(Long id_user, User user);

    void deleteUser(Long id_user);

    User loginUser(String user_name, String password);

    User RegisterUser(String user_name, String email, String password);

    User updatePassword(Long id_user, String newPassword);

}
