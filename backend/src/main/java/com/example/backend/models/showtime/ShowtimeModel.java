package com.example.backend.models.showtime;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "showtimes")
@Builder
public class ShowtimeModel {

    @GeneratedValue
    @Id
    public UUID id;
    public Date date;
    public Time time;

    @Builder.Default
    public int availableSeats = 0;
  
    @Embedded
    public MovieTheater movieTheater;
 
}
