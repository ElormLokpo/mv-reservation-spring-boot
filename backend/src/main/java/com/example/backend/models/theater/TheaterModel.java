package com.example.backend.models.theater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.showtime.ShowtimeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // list of showtimes

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    public Set<SeatModel> seats = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    public CinemaModel cinema;

}
