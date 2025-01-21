package com.example.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.tickets.TicketModel;

@Repository
public interface TicketRepository
        extends JpaRepository<TicketModel, UUID>, PagingAndSortingRepository<TicketModel, UUID> {
}
