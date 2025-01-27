package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.models.cinema.CinemaModel;

@Mapper
public interface CinemaMapper {
    public CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

    CinemaModel createCinemaDtotoModel(CreateCinemaDto cinemaDto);
    GetCinemaDto cinemaModelToDto(CinemaModel cinema);
}
