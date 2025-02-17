package com.example.backend.daos;

import com.example.backend.dtos.user.AuthResponseDto;
import com.example.backend.dtos.user.LoginUserDto;
import com.example.backend.models.user.UserModel;

public interface UserDao {
    Object registerUser(UserModel user) throws Exception;

    AuthResponseDto loginUser(LoginUserDto loginDto) throws Exception;
}
