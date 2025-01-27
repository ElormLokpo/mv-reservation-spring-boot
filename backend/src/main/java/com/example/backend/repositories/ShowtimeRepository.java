package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.showtime.ShowtimeModel;

@Repository
public interface ShowtimeRepository
        extends JpaRepository<ShowtimeModel, UUID>{

}
