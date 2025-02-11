package com.example.backend.dtos.user;

import com.example.backend.models.user.UserRolesEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    String username;
    String password;
    UserRolesEnum role;
}
