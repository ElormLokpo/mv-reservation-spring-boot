package com.example.backend.controllers;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.movie.MovieModel;
import com.example.backend.services.MovieService;
import com.example.backend.utils.ResponseGen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/movies")
@PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService _movieService) {
        this.movieService = _movieService;
    }

    @GetMapping
    public ResponseEntity<ResponseGen> getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        ResponseDto moviesResponseDto = movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(moviesResponseDto);

        response.setSuccess(true);
        response.setMessage("All movies query successful");

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseGen> getMovie(@PathVariable UUID id) {
        MovieModel movie = movieService.getMovie(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Movie query successful")
                .data(movie)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseGen> createMovie(@RequestBody CreateMovieDto moviedto) {
        MovieModel movieCreated = movieService.createMovie(moviedto);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Movie creation successul")
                .data(movieCreated)
                .build();

        return ResponseEntity.ok(response);
    }

    // Work on update logic
    @PatchMapping(path = "{id}")
    public ResponseEntity<ResponseGen> updateMovie(@PathVariable UUID id, @RequestBody CreateMovieDto movieDto) {
        Boolean movieUpdated = movieService.updateMovie(id, movieDto);
        ResponseGen response;

        if (movieUpdated == true) {
            response = ResponseGen.builder()
                    .success(true)
                    .message("Movie updated successully")
                    .data(null)
                    .build();
        } else {
            response = ResponseGen.builder()
                    .success(false)
                    .message("Movie not found")
                    .data(null)
                    .build();
        }

        return ResponseEntity.ok(response);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ResponseGen> deleteMovie(@PathVariable UUID id) {
        MovieModel movie = movieService.deleteMovie(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Movie delete successful")
                .data(movie)
                .build();

        return ResponseEntity.ok(response);

    }

}
