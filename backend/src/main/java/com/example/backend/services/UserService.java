package com.example.backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.models.user.UserModel;
import com.example.backend.repositories.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository _userRepository) {
        this.userRepository = _userRepository;
    }

    //remember to implement user dao
    public UserModel registerUser(UserModel user) {
        UserModel userFound = userRepository.findUserByUsername(user.getUsername());

        if (userFound == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        return null;
    }
}
