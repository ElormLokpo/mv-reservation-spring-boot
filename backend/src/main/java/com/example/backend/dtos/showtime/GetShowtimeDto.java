package com.example.backend.dtos.showtime;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.backend.models.showtime.MovieTheater;
import lombok.Data;

@Data
public class GetShowtimeDto {
    public LocalDate date;
    public LocalTime time;
    public int availableSeats;

    public MovieTheater movieTheater;
}
