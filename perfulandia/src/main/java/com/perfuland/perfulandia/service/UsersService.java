package com.perfuland.perfulandia.service;

import java.util.List;

import com.perfuland.perfulandia.model.User;

public interface UsersService {

    List<User> getAllUsers();

    User getUserById(Long idUser);

    User createUser(User user);

    User updateUser(Long idUser, User user);

    void deleteUser(Long idUser);

    User loginUser(String userName, String password);

    User RegisterUser(String userName, String email, String password);

    User updatePassword(Long idUser, String newPassword);

}