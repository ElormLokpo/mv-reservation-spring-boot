package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dtos.seat.GetSeatDto;
import com.example.backend.models.seats.SeatModel;

@Mapper
public interface SeatMapper {
    
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);

    public GetSeatDto SeatToDto(SeatModel seatModel);
}
