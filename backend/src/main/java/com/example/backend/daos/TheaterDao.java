package com.example.backend.daos;

import java.util.Optional;
import java.util.UUID;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.models.theater.TheaterModel;

public interface TheaterDao {
    public ResponseDto getAllTheaters(int pageNo, int pageSize, String sortBy, String sortDir);

    public Optional<TheaterModel> getTheater(UUID id);

    public TheaterModel createTheater(CreateTheaterDto theaterDto);

    public TheaterModel deleteTheater(UUID id);
}
