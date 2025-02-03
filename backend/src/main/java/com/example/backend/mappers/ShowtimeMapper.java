package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dtos.showtime.CreateShowtimeDto;
import com.example.backend.dtos.showtime.GetShowtimeDto;
import com.example.backend.models.showtime.ShowtimeModel;

@Mapper
public interface ShowtimeMapper {
    ShowtimeMapper INSTANCE = Mappers.getMapper(ShowtimeMapper.class);

    ShowtimeModel ShowtimeDtoToModel(CreateShowtimeDto showtimeDto);
    GetShowtimeDto ShowtimeToDtoModel(ShowtimeModel showtime);
}
