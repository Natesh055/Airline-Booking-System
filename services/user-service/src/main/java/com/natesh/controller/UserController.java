package com.natesh.controller;

import com.natesh.payload.DTO.UserDTO;
import com.natesh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(
            @RequestHeader("X-User-Email") String email) throws Exception {
        UserDTO user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-by/{id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("id") Long id) throws Exception {
        UserDTO userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
