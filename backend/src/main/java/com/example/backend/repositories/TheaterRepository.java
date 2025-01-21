package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.theater.TheaterModel;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterModel, UUID>, PagingAndSortingRepository<TheaterModel, UUID>{}
