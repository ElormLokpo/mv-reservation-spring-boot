package com.example.backend.dtos.movie;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

import com.example.backend.models.showtime.ShowtimeModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMoviesDto {
    public UUID id;
    public String title;
    public String genre;
    public Date releaseDate;
    public Integer duration;
    public Collection<ShowtimeModel> showtimes;
}
