package com.example.backend.repositories;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.models.seats.SeatModel;

@Repository
public interface SeatRepository extends JpaRepository<SeatModel, UUID> {
    @Query("SELECT s from seats s WHERE s.theater.id = :theaterId")
    Page<SeatModel> findSeatsByTheater(@Param("theaterId") UUID theaterId, Pageable pageable);

    @Query("SELECT s from seats s WHERE s.theater.id = :theaterId")
    Collection<SeatModel> findSeatsByTheaterR(@Param("theaterId") UUID theaterId);
}
