package com.natesh.mapper;

import com.natesh.model.User;
import com.natesh.payload.DTO.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null)
            return null;
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .lastLoginTime(user.getLastLogin())
                .phone(user.getPhone())
                .build();
    }

    public static List<UserDTO> toDTOList(List<User> user) {
        return user.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
