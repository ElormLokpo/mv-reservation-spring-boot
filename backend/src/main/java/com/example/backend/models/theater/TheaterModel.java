package com.example.backend.models.theater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.showtime.ShowtimeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Theater name required")
    public String name;
    public String location;

    @NotEmpty(message = "Theater seating capacity required")
    public Integer seatingCapacity;

    // list of showtimes
    @Builder.Default
    @OneToMany(mappedBy = "theater")
    public Collection<ShowtimeModel> showTimes = new ArrayList<>();

    // list of seats
    @Builder.Default
    @OneToMany(mappedBy = "theaterSeat")
    public Collection<SeatModel> seats = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    public CinemaModel cinema;
}
