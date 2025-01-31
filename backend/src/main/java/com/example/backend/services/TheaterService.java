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

import com.example.backend.daos.TheaterDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.dtos.theater.GetTheaterDto;
import com.example.backend.mappers.CinemaMapper;
import com.example.backend.mappers.TheaterMapper;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.repositories.CinemaRepository;
import com.example.backend.repositories.TheaterRepository;

@Service
public class TheaterService implements TheaterDao {

    TheaterRepository theaterReqRepository;
    CinemaService cinemaService;

    public TheaterService(TheaterRepository _theaterRepository, CinemaService _cinemaService) {

        this.theaterReqRepository = _theaterRepository;
        this.cinemaService = _cinemaService;
    }

    @Override
    public ResponseDto getAllTheatersByCinema(UUID cinemaId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<TheaterModel> theaterQuery = theaterReqRepository.findTheatersByCinema(cinemaId, pageable);

        Collection<TheaterModel> theaterList = theaterQuery.getContent();

        Collection<GetTheaterDto> theaterListDto = theaterList.stream()
                .map(theater -> TheaterMapper.INSTANCE.theaterToDto(theater)).collect(Collectors.toList());

        ResponseDto response = ResponseDto.builder()
                .data(theaterListDto)
                .pageSize(theaterQuery.getSize())
                .pageNumber(theaterQuery.getNumber())
                .totalElements(theaterQuery.getTotalElements())
                .totalPages(theaterQuery.getTotalPages())
                .isLastPage(theaterQuery.isLast())
                .build();

        return response;

    }

    @Override
    public ResponseDto getAllTheaters(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<TheaterModel> theaterPage = theaterReqRepository.findAll(pageable);

        Collection<TheaterModel> theaterList = theaterPage.getContent();

        Collection<GetTheaterDto> theaterListDto = theaterList.stream()
                .map(theater -> TheaterMapper.INSTANCE.theaterToDto(theater)).collect(Collectors.toList());

        ResponseDto response = ResponseDto.builder()
                .data(theaterListDto)
                .pageSize(theaterPage.getSize())
                .pageNumber(theaterPage.getNumber())
                .totalElements(theaterPage.getTotalElements())
                .totalPages(theaterPage.getTotalPages())
                .isLastPage(theaterPage.isLast())
                .build();

        return response;
    }

    @Override
    public Optional<TheaterModel> getTheater(UUID id) {
        return theaterReqRepository.findById(id);
    }

    @Override
    public TheaterModel createTheater(UUID cinemaId, CreateTheaterDto theaterDto) {

        CinemaModel cinemaFound = cinemaService.getCinema(cinemaId).orElse(null);

        if (cinemaFound != null) {
            TheaterModel newTheater = TheaterModel.builder()
                    .name(theaterDto.getName())
                    .location(theaterDto.getLocation())
                    .seatingCapacity(theaterDto.getSeatingCapacity())
                    .build();

            newTheater.setCinema(cinemaFound);

            theaterReqRepository.save(newTheater);

            return newTheater;
        }

        return null;
    }

    @Override
    public TheaterModel deleteTheater(UUID id) {
        Boolean theaterExists = theaterReqRepository.existsById(id);
        TheaterModel theater = theaterReqRepository.findById(id).orElse(null);

        if (theaterExists) {
            theaterReqRepository.deleteById(id);
        }

        return theater;
    }

}
