package com.example.backend.dtos.movie;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovieDto {

    public String title;
    public String description;
    public String genre;
    public Date releaseDate;
    public Double rating;
    public Integer duration;
    public String image;
}
