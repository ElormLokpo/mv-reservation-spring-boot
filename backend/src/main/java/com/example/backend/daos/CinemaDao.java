package com.example.backend.daos;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.models.cinema.CinemaModel;

public interface CinemaDao {
    public Collection<GetCinemaDto> getAllCinema();

    public Optional<CinemaModel> getCinema(UUID id);

    public Boolean createCinema(CreateCinemaDto cinemaDto);

    public Boolean deleteCinema(UUID id);

}
