package com.example.backend.daos;


import java.util.Optional;
import java.util.UUID;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.seat.CreateSeatDto;

import com.example.backend.models.seats.SeatModel;


public interface SeatDao {

    public ResponseDto getAllSeatsByTheater(UUID theaterId, int pageNo, int pageSize, String sortBy, String sortDir);

    public Optional<SeatModel> getSeat(UUID id);

    public SeatModel createSeat(UUID theaterId, CreateSeatDto seatDto);

    public SeatModel deleteSeat(UUID id);
}
