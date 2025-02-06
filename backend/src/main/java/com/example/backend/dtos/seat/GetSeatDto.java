package com.example.backend.dtos.seat;

import java.util.UUID;

import com.example.backend.models.seats.SeatStatusEnum;
import com.example.backend.models.theater.TheaterModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetSeatDto {
    public UUID id;
    public Integer slabel;

    public SeatStatusEnum status = SeatStatusEnum.Available;

    @JsonIgnore
    public TheaterModel theater;
}
