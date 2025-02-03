package com.example.backend.models.movie;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message="Movie title is required")
    public String title; 
    public String description;
    public String genre;
    public Date releaseDate;
    public Double rating;
    public Integer duration;
    public String image;
    
    
}
