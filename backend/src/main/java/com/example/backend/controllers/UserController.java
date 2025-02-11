package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.user.CreateUserDto;
import com.example.backend.mappers.UserMapper;
import com.example.backend.models.user.UserModel;
import com.example.backend.models.user.UserRolesEnum;
import com.example.backend.services.UserService;
import com.example.backend.utils.ResponseGen;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("auth")
@RestController
public class UserController {
    
    UserService userService;

    public UserController(UserService _userService){
        this.userService = _userService;
    }

    @PostMapping("register")
    public ResponseEntity<ResponseGen> registerUser(@RequestBody CreateUserDto userDto) {
       
        ResponseGen response = ResponseGen.builder().build();
       
        if (userDto.getRole() != UserRolesEnum.CLERK){
            UserModel userCreated = userService.registerUser(UserMapper.INSTANCE.userDtoToModel(userDto));
            response.setSuccess(true);
            response.setMessage("User created sucessfully");
            response.setData(userCreated);
        }

        response.setSuccess(false);
        response.setMessage("Only admins can register account"); //handle exception better.
        response.setData(null);
       

        return ResponseEntity.ok(response);
    }
    
}
