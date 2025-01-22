package com.example.backend.daos;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.dtos.movie.GetMoviesDto;
import com.example.backend.models.movie.MovieModel;

public interface MovieDao {
    public Collection<GetMoviesDto> getAllMovies();

    public Optional<MovieModel> getMovie(UUID id);

    public Boolean createMovie(CreateMovieDto movieDto);

    public Boolean updateMovie(UUID id, CreateMovieDto movieDto);

    public Boolean deleteMovie(UUID id);
}
