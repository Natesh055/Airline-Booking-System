package com.natesh.payload.response;

import com.natesh.payload.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String jwt;
    private String message;
    private String title;
    private UserDTO user;
}
