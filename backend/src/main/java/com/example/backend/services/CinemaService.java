package com.example.backend.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.example.backend.daos.CinemaDao;
import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.mappers.CinemaMapper;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.repositories.CinemaRepository;

@Service
public class CinemaService implements CinemaDao {

    CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository _cinemaRepository) {
        this.cinemaRepository = _cinemaRepository;
    }

    @Override
    public Collection<GetCinemaDto> getAllCinema() {
        Collection<CinemaModel> allCinema = cinemaRepository.findAll();
        return allCinema.stream().map(cinema -> CinemaMapper.INSTANCE.cinemaModelToDto(cinema))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CinemaModel> getCinema(UUID id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public Boolean createCinema(CreateCinemaDto cinemaDto) {
        cinemaRepository.save(CinemaMapper.INSTANCE.createCinemaDtotoModel(cinemaDto));
        return true;
    }

    @Override
    public Boolean deleteCinema(UUID id) {
        Boolean cinemaExists = cinemaRepository.existsById(id);

        if(cinemaExists == true){
            cinemaRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
