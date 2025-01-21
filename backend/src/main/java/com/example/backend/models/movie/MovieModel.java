package com.example.backend.models.movie;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;



import com.example.backend.models.showtime.ShowtimeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movies")
@Builder
public class MovieModel {
    
    @GeneratedValue
    @Id
    public UUID id; 
    public String title; 
    public String description;
    public String genre;
    public Date releaseDate;
    public Double rating;
    public Integer duration;
    public String image;
    
    @OneToMany(mappedBy="movie")
    public Collection<ShowtimeModel> showtimes;

    
}
