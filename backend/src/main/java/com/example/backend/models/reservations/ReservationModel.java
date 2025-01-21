package com.example.backend.models.reservations;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.UUID;

import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.showtime.ShowtimeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="reservations")
@Builder
public class ReservationModel {

    @GeneratedValue
    @Id
    public UUID id; 
    public Date date; 
    public Time time;
    public Double totalPrice;

    //Customer 
    
    @OneToOne
    @JoinColumn(name="showtime")
    public ShowtimeModel showtime;

   
    @OneToMany(mappedBy="reservation")
    public Collection<SeatModel> seats;
}
