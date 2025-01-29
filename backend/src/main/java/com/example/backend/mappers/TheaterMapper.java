package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.dtos.theater.GetTheaterDto;
import com.example.backend.models.theater.TheaterModel;

@Mapper
public interface TheaterMapper {
    public TheaterMapper INSTANCE = Mappers.getMapper(TheaterMapper.class);

    TheaterModel theaterDtoToModel(CreateTheaterDto theaterDto);
    GetTheaterDto theaterToDto(TheaterModel theater);

}
