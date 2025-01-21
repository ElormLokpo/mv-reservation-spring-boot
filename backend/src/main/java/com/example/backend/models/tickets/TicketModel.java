package com.example.backend.models.tickets;

import java.sql.Time;
import java.util.UUID;

import com.example.backend.models.reservations.ReservationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tickets")
@Builder
public class TicketModel {
    
    @GeneratedValue
    @Id
    public UUID id;
    public Double price;
    public Time issuedTime;
    
    @OneToOne
    @JoinColumn(name="reservation_id")
    public ReservationModel reservation;
}
