package com.example.backend.repositories;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.tickets.TicketModel;

@Repository
public interface TicketRepository
        extends JpaRepository<TicketModel, UUID>{

        @Query("SELECT t from tickets t WHERE t.showtime.id = :showtimeId")
        public Page<TicketModel> findTicketsByShowtime(UUID showtimeId, Pageable pageable);
}
