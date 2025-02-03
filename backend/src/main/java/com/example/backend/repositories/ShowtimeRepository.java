package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.showtime.ShowtimeModel;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowtimeModel, UUID> {
        @Query("SELECT s from showtimes s WHERE s.movieTheater.movieId = :movieId")
        public Page<ShowtimeModel> findShowtimesByMovie(UUID movieId, Pageable pageable);

        @Query("SELECT s from showtimes s WHERE s.movieTheater.theaterId = :theaterId")
        public Page<ShowtimeModel> findShowTimesByTheater(UUID theaterId, Pageable pageable);
}
