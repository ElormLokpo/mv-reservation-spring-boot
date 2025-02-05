package com.example.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.daos.TheaterDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.dtos.theater.GetTheaterDto;
import com.example.backend.exceptions.ResourceNotfoundException;
import com.example.backend.mappers.TheaterMapper;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.repositories.SeatRepository;
import com.example.backend.repositories.TheaterRepository;

@Service
public class TheaterService implements TheaterDao {

        TheaterRepository theaterReqRepository;
        CinemaService cinemaService;
        SeatRepository seatRepository;

        public TheaterService(TheaterRepository _theaterRepository, CinemaService _cinemaService,
                        SeatRepository _seatRepository) {
                this.theaterReqRepository = _theaterRepository;
                this.cinemaService = _cinemaService;
                this.seatRepository = _seatRepository;
        }

        @Override
        public ResponseDto getAllTheatersByCinema(UUID cinemaId, int pageNo, int pageSize, String sortBy,
                        String sortDir) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<TheaterModel> theaterQuery = theaterReqRepository.findTheatersByCinema(cinemaId, pageable);

                Collection<TheaterModel> theaterList = theaterQuery.getContent();

                Collection<GetTheaterDto> theaterListDto = theaterList.stream()
                                .map(theater -> TheaterMapper.INSTANCE.theaterToDto(theater))
                                .collect(Collectors.toList());

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
                                .map(theater -> TheaterMapper.INSTANCE.theaterToDto(theater))
                                .collect(Collectors.toList());

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
        public TheaterModel getTheater(UUID id) {
                return theaterReqRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));
        }

        @Override
        public TheaterModel createTheater(UUID cinemaId, CreateTheaterDto theaterDto) {

                CinemaModel cinemaFound = cinemaService.getCinema(cinemaId);

                TheaterModel newTheater = TheaterModel.builder()
                                .name(theaterDto.getName())
                                .location(theaterDto.getLocation())
                                .seatingCapacity(theaterDto.getSeatingCapacity())
                                .build();

                newTheater.setCinema(cinemaFound);

                TheaterModel theaterCreated = theaterReqRepository.save(newTheater);

                ArrayList<SeatModel> seats = new ArrayList<SeatModel>();

                for (int i = 1; i <= theaterDto.getSeatingCapacity(); i++) {
                        seats.add(
                                        SeatModel.builder()
                                                        .slabel(i)
                                                        .theater(theaterCreated)
                                                        .build());
                }

                seatRepository.saveAll(seats);

                return newTheater;

        }

        @Override
        public TheaterModel deleteTheater(UUID id) {
                TheaterModel theater = theaterReqRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));

                theaterReqRepository.deleteById(id);

                return theater;
        }

}
