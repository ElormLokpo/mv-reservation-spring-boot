package com.example.backend.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.daos.MovieDao;
import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.dtos.movie.GetMoviesDto;
import com.example.backend.mappers.MovieMapper;
import com.example.backend.models.movie.MovieModel;
import com.example.backend.repositories.MovieRepository;

@Service
public class MovieService implements MovieDao {

    MovieRepository movieRepository;

    public MovieService(MovieRepository _movieRepository) {
        this.movieRepository = _movieRepository;
    }

    @Override
    public Collection<GetMoviesDto> getAllMovies() {
        Collection<MovieModel> allMovies = movieRepository.findAll();
        Collection<GetMoviesDto> allMoviesDto = allMovies.stream().map(movie -> MovieMapper.INSTANCE.movieToDto(movie))
                .collect(Collectors.toList());

        return allMoviesDto;
    }

    @Override
    public Optional<MovieModel> getMovie(UUID id) {
        return movieRepository.findById(id);
    }

    @Override
    public Boolean createMovie(CreateMovieDto movieDto) {
        MovieModel movie = MovieMapper.INSTANCE.movieDtoToModel(movieDto);
        movieRepository.save(movie);

        return true;
    }

    @Override
    public Boolean updateMovie(UUID id, CreateMovieDto movieDto) {
        MovieModel foundMovie = movieRepository.findById(id).orElse(null);

        if (foundMovie != null) {
            foundMovie.setTitle(movieDto.title);
            foundMovie.setDescription(movieDto.description);
            foundMovie.setGenre(movieDto.genre);
            foundMovie.setReleaseDate(movieDto.releaseDate);
            foundMovie.setRating(movieDto.rating);
            foundMovie.setDuration(movieDto.duration);
            foundMovie.setImage(movieDto.image);

            movieRepository.save(foundMovie);

            return true;
        }

        return false;

    }

    @Override
    public Boolean deleteMovie(UUID id) {
        Boolean movieFound = movieRepository.existsById(id);
        if (movieFound == true) {
            movieRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
