package com.example.backend.dtos.showtime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.example.backend.models.showtime.MovieTheater;
import lombok.Data;

@Data
public class GetShowtimeDto {
    public UUID id;
    public LocalDate date;
    public LocalTime time;
    public int availableSeats;
    public Double rate;
    public MovieTheater movieTheater;
}
