package com.example.backend.models.showtime;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import com.example.backend.models.movie.MovieModel;
import com.example.backend.models.theater.TheaterModel;

import jakarta.persistence.Entity;
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
@Entity(name="showtimes")
@Builder
public class ShowtimeModel {
    
    @GeneratedValue
    @Id
    public UUID id;
    public Date date;
    public Time time;
    public int availableSeats;
   
    //Movie rel
   


   //Theater rel
  

}
