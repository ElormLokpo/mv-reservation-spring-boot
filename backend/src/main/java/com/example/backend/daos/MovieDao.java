package com.example.backend.daos;

import java.util.UUID;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.models.movie.MovieModel;

public interface MovieDao {
    public ResponseDto getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir);

    public MovieModel getMovie(UUID id);

    public MovieModel createMovie(CreateMovieDto movieDto);

    public Boolean updateMovie(UUID id, CreateMovieDto movieDto);

    public MovieModel deleteMovie(UUID id);
}
