package com.example.backend.models.showtime;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.backend.models.movie.MovieModel;
import com.example.backend.models.theater.TheaterModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity(name = "showtimes")
@Builder
public class ShowtimeModel {

    @GeneratedValue
    @Id
    public UUID id;
    public Date date;
    public Time time;
    public int availableSeats;
  

    // Movie rel
    @OneToMany(mappedBy = "showtime", fetch = FetchType.LAZY)
    public Collection<MovieModel> movie = new ArrayList<>();

    // Theater rel
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    public Set<TheaterModel> theater = new HashSet<>();

}
