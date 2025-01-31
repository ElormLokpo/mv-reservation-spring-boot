package com.example.backend.dtos.movie;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
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
