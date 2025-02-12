package com.example.backend.models.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    UserModel user;

    public UserPrincipal(UserModel _user) {
        this.user = _user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

       return Collections.singleton(new SimpleGrantedAuthority("Admin"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
