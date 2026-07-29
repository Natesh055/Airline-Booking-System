package com.natesh.service;

import com.natesh.model.User;
import com.natesh.payload.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String email) throws Exception;

    UserDTO getUserById(Long id) throws Exception;

    List<UserDTO> getAllUsers();

}
