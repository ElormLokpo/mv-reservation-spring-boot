package com.example.backend.dtos.seat;

import java.util.UUID;

import com.example.backend.models.seats.SeatStatusEnum;
import com.example.backend.models.theater.TheaterModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetSeatDto {
    public UUID id;
    public Integer srow;
    public Integer scolumn;

    public SeatStatusEnum status = SeatStatusEnum.Available;

    // Reservation rel

    // Theater rel
    public TheaterModel theater;
}
