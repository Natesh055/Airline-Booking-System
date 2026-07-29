package com.natesh.controller;

import com.natesh.payload.DTO.UserDTO;
import com.natesh.payload.request.LoginRequest;
import com.natesh.payload.response.AuthResponse;
import com.natesh.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserDTO userDTO) throws Exception {
        AuthResponse authResponse = authService.signup(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse authResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
