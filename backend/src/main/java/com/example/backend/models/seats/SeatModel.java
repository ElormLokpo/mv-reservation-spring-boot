package com.example.backend.models.seats;

import java.util.UUID;

import com.example.backend.models.reservations.ReservationModel;
import com.example.backend.models.theater.TheaterModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="seats")
@Builder
public class SeatModel {
    @GeneratedValue
    @Id 
    public UUID id;
    public Integer row;
    public Integer column;
    
    @Enumerated(EnumType.STRING)  
    public SeatStatusEnum status;

    @ManyToOne
    @JoinColumn(name="reservation_id")
    public ReservationModel reservation;    

    @ManyToOne 
    @JoinColumn(name="theater_id")
    public TheaterModel theaterSeat;

}
