package com.example.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.backend.models.user.UserModel;
import com.example.backend.models.user.UserPrincipal;
import com.example.backend.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userFound = userRepository.findUserByUsername(username);
        if (userFound == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return new UserPrincipal(userFound);
    }

}
