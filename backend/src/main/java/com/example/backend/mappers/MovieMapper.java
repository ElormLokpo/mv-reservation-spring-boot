package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dtos.movie.CreateMovieDto;
import com.example.backend.dtos.movie.GetMoviesDto;
import com.example.backend.models.movie.MovieModel;

@Mapper
public interface MovieMapper{
    public MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieModel movieDtoToModel (CreateMovieDto movieDto);
    GetMoviesDto movieToDto (MovieModel model);
}