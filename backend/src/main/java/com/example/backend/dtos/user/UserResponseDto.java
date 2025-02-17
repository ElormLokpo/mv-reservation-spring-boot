package com.example.backend.dtos.user;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    public UUID id;
    public String username;
}
