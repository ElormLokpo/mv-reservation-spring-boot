package com.example.backend.dtos.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {
    public String username;
    public String password;
}
