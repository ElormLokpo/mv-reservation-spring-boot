package com.example.backend.dtos.movie;

import java.sql.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
;

@Data

@Builder
public class GetMoviesDto {
    public UUID id;
    public String title;
    public String genre;
    public Date releaseDate;
    public Integer duration;
   
}
