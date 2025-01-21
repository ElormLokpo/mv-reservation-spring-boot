package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.seats.SeatModel;

@Repository
public interface SeatRepository extends JpaRepository<SeatModel, UUID>, PagingAndSortingRepository<SeatModel, UUID> {

}
