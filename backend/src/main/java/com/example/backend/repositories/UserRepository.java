package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    @Query("SELECT u FROM users u WHERE u.username = :username")
    UserModel findUserByUsername(String username);

}
