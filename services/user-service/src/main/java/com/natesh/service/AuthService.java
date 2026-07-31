package com.natesh.service;

import com.natesh.payload.DTO.UserDTO;
import com.natesh.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signup(UserDTO request) throws Exception;

}
