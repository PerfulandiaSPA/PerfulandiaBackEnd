package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.User;

interface UsersService {
    // Deberia haber un servicio para usuarios, no para perfumes en este archivo

    List<User> getAllUsers();

    User getUserById(Long id_user);

    User createUser(User user);

    User updateUser(Long id_user, User user);

    void deleteUser(Long id_user);

}
