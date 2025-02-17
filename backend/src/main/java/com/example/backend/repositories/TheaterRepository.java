package com.example.backend.repositories;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.models.theater.TheaterModel;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterModel, UUID> {
    @Query("SELECT t from theaters t WHERE t.cinema.id = :cinemaId")
    Page<TheaterModel> findTheatersByCinema(@Param("cinemaId") UUID cinemaId, Pageable pageable);
}
