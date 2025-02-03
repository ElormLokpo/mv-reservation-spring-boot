package com.example.backend.dtos.showtime;

import java.sql.Date;
import java.sql.Time;
import com.example.backend.models.showtime.MovieTheater;
import lombok.Data;

@Data
public class GetShowtimeDto {
    public Date date;
    public Time time;
    public int availableSeats;

    public MovieTheater movieTheater;
}
