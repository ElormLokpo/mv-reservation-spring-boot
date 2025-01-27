package com.example.backend.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.daos.CinemaDao;
import com.example.backend.dtos.ResponseDto;
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
    public ResponseDto getAllCinema(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<CinemaModel> allCinema = cinemaRepository.findAll(pageable);

        Collection<CinemaModel> allCinemaContent = allCinema.getContent();
        Collection<GetCinemaDto> cinemaDtoCollection = allCinemaContent.stream()
                .map(cinema -> CinemaMapper.INSTANCE.cinemaModelToDto(cinema))
                .collect(Collectors.toList());

        ResponseDto responseDto = ResponseDto.builder()
                .pageSize(allCinema.getSize())
                .pageNumber(allCinema.getNumber() + 1)
                .totalElements(allCinema.getTotalElements())
                .totalPages(allCinema.getTotalPages())
                .isLastPage(allCinema.isLast())
                .data(cinemaDtoCollection)
                .build();

        return responseDto;
    }

    @Override
    public Optional<CinemaModel> getCinema(UUID id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public CinemaModel createCinema(CreateCinemaDto cinemaDto) {
        CinemaModel cinema = CinemaMapper.INSTANCE.createCinemaDtotoModel(cinemaDto);
        cinemaRepository.save(cinema);

        return cinema;
    }

    @Override
    public CinemaModel deleteCinema(UUID id) {
        Boolean cinemaExists = cinemaRepository.existsById(id);
        CinemaModel cinema = cinemaRepository.findById(id).orElse(null);

        if (cinemaExists == true) {
            cinemaRepository.deleteById(id);
            return cinema;
        }

        return cinema;
    }

}
