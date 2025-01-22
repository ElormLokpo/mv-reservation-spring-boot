package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.mappers.CinemaMapper;
import com.example.backend.models.cinema.CinemaModel;

@Repository
public interface CinemaRepository
                extends JpaRepository<CinemaModel, UUID>, PagingAndSortingRepository<CinemaModel, UUID> {
}
