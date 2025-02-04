package com.example.backend.dtos.showtime;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.backend.models.showtime.MovieTheater;
import lombok.Data;

@Data
public class CreateShowtimeDto {
    public LocalDate date;
    public LocalTime time;

    public MovieTheater movieTheater;

}
