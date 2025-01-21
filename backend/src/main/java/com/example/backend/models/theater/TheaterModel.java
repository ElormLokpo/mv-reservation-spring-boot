package com.example.backend.models.theater;

import java.util.Collection;
import java.util.UUID;

import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.showtime.ShowtimeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "theaters")
@Builder
public class TheaterModel {
    
    @GeneratedValue
    @Id
    public UUID id;
    public String name; 
    public String location; 
    public Integer seatingCapacity; 
    //list of showtimes
    @OneToMany(mappedBy="theater")
    public Collection<ShowtimeModel> showTimes;

    //list of seats
    @OneToMany(mappedBy = "theaterSeat")
    public Collection<SeatModel> seats;
}
