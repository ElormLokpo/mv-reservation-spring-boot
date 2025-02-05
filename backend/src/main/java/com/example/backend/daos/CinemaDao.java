package com.example.backend.daos;

import java.util.UUID;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.models.cinema.CinemaModel;

public interface CinemaDao {
    public ResponseDto getAllCinema(int pageNo, int pageSize, String sortBy, String sortDir);

    public CinemaModel getCinema(UUID id);

    public CinemaModel createCinema(CreateCinemaDto cinemaDto);

    public CinemaModel deleteCinema(UUID id);

}
