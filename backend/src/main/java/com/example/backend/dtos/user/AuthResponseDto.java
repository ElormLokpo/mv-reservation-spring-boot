package com.example.backend.dtos.user;

import com.example.backend.models.user.UserModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    UserModel user;
    String token;
}
