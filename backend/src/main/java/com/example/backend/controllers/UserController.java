package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.user.AuthResponseDto;
import com.example.backend.dtos.user.CreateUserDto;
import com.example.backend.dtos.user.LoginUserDto;
import com.example.backend.mappers.UserMapper;
import com.example.backend.models.user.UserRolesEnum;
import com.example.backend.services.UserService;
import com.example.backend.utils.ResponseGen;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("api/auth")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService _userService) {
        this.userService = _userService;
    }

    @PostMapping("register")
    public ResponseEntity<ResponseGen> registerUser(@RequestBody CreateUserDto userDto) throws Exception {

        ResponseGen response = ResponseGen.builder().build();

        if (userDto.getRole() == UserRolesEnum.CLERK) {
            throw new AuthException("Only admins can register accounts. Clerks have to be added by admin");

        }

        Object authResponse = userService.registerUser(UserMapper.INSTANCE.userDtoToModel(userDto));
        response.setSuccess(true);
        response.setMessage("User registered sucessfully.");
        response.setData(authResponse);

        return ResponseEntity.ok(response);

    }

    @PostMapping("add-clerk")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseGen> addClerk(@RequestBody CreateUserDto userDto) throws Exception {

        ResponseGen response = ResponseGen.builder().build();

        if (userDto.getRole() == UserRolesEnum.ADMIN) {
            throw new AuthException("Admins cannot add other admins...kindly refer to endpoint: /api/auth/register");

        }

        Object authResponse = userService.registerUser(UserMapper.INSTANCE.userDtoToModel(userDto));
        response.setSuccess(true);
        response.setMessage("Clerk added sucessfully.");
        response.setData(authResponse);

        return ResponseEntity.ok(response);

    }

    @PostMapping("login")
    public ResponseEntity<ResponseGen> loginUser(@RequestBody LoginUserDto loginDto) throws Exception {
        AuthResponseDto loggedInUser = userService.loginUser(loginDto);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Login successful..")
                .data(loggedInUser)
                .build();

        return ResponseEntity.ok(response);
    }

}
