package com.natesh.service.impl;

import com.natesh.config.JwtProvider;
import com.natesh.enums.UserRole;
import com.natesh.mapper.UserMapper;
import com.natesh.model.User;
import com.natesh.payload.DTO.UserDTO;
import com.natesh.payload.response.AuthResponse;
import com.natesh.repository.UserRepository;
import com.natesh.service.AuthService;
import com.natesh.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtProvider jwtProvider;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signup(UserDTO request) throws Exception {
        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new Exception("Cannot create user, already exists");
        }
        if (request.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("Cannot create system admin");
        }
        User newUser = User.builder()
                .id(request.getId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .lastLogin(LocalDate.from(LocalDateTime.now()))
                .createdAt(LocalDate.from(LocalDateTime.now()))
                .build();

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());

        String jwt = jwtProvider.generateToken(
                authentication, savedUser.getId()
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));
        authResponse.setTitle("Welcome " + savedUser.getFullName());
        authResponse.setMessage("Registration succesfull");

        return authResponse;
    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {
        Authentication authentication = authenticate(email, password);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("");
        }
        user.setLastLogin(LocalDate.from(LocalDateTime.now()));
        userRepository.save(user);
        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome " + user.getFullName());
        authResponse.setMessage("Login succesfull");
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(
                password, userDetails.getPassword()
        )) {
            throw new Exception("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }
}
