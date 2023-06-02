package com.example.week8task.service;

import com.example.week8task.dto.LoginDTO;
import com.example.week8task.dto.UserDTO;
import com.example.week8task.entity.User;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDTO userDTO);

    boolean loginUser(LoginDTO loginDTO);
    User getUserByEmail(String email);

    User getUserByUsername(String userName);
     User getUserByPassword(String password);

    UserDTO getUserById(Long userId);

    List<User> getAllUsers();
}
