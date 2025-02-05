package com.example.backend.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.backend.daos.MovieDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.dtos.movie.GetMoviesDto;
import com.example.backend.exceptions.ResourceNotfoundException;
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
    public ResponseDto getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<MovieModel> moviePage = movieRepository.findAll(pageable);

        Collection<MovieModel> allMoviesContent = moviePage.getContent();
        Collection<GetMoviesDto> allMoviesDto = allMoviesContent.stream()
                .map(movie -> MovieMapper.INSTANCE.movieToDto(movie))
                .collect(Collectors.toList());

        ResponseDto response = ResponseDto.builder()
                .data(allMoviesDto)
                .pageSize(moviePage.getSize())
                .pageNumber(moviePage.getNumber())
                .totalElements(moviePage.getTotalElements())
                .totalPages(moviePage.getTotalPages())
                .isLastPage(moviePage.isLast())
                .build();

        return response;
    }

    @Override
    public MovieModel getMovie(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));
    }

    @Override
    public MovieModel createMovie(CreateMovieDto movieDto) {
        return movieRepository.save(MovieMapper.INSTANCE.movieDtoToModel(movieDto));
    }

    // work on update logic better
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
    public MovieModel deleteMovie(UUID id) {

        MovieModel movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));
        movieRepository.deleteById(id);

        return movie;
    }

}
