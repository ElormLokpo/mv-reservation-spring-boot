package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend.daos.UserDao;
import com.example.backend.dtos.user.AuthResponseDto;
import com.example.backend.dtos.user.ClerkResponseDto;
import com.example.backend.dtos.user.LoginUserDto;
import com.example.backend.mappers.UserMapper;
import com.example.backend.models.user.UserModel;
import com.example.backend.models.user.UserRolesEnum;
import com.example.backend.repositories.UserRepository;
import jakarta.security.auth.message.AuthException;

@Service
public class UserService implements UserDao {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    public UserService(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

  
    public Object registerUser(UserModel user) throws Exception {
        UserModel userFound = userRepository.findUserByUsername(user.getUsername());

        if(user.getRole() == UserRolesEnum.CLERK){
            if (userFound != null) {
                throw new AuthException("User with username: " + user.getUsername() + " already exists.");
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            UserModel createdUser = userRepository.save(user);
            return ClerkResponseDto.builder()
            .user(UserMapper.INSTANCE.userToDto(createdUser))
            .build();
        }

        if (userFound != null) {
            throw new AuthException("User with username: " + user.getUsername() + " already exists.");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserModel createdUser = userRepository.save(user);
        return AuthResponseDto.builder()
        .user(UserMapper.INSTANCE.userToDto(createdUser))
        .token(jwtService.generateToken(createdUser.getUsername()))
        .build();
    }



    public AuthResponseDto loginUser(LoginUserDto loginDto) throws Exception {
        UserModel userFound = userRepository.findUserByUsername(loginDto.getUsername());

        if (userFound == null) {
            throw new AuthException("User with username: " + loginDto.getUsername() + " does NOT exist.");
        }

        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new AuthException("Incorrect password");
        }

        return AuthResponseDto.builder()
        .user(UserMapper.INSTANCE.userToDto(userFound))
        .token(jwtService.generateToken(userFound.getUsername()))
        .build();

    }
}
