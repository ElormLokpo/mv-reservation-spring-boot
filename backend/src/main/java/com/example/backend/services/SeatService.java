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

import com.example.backend.daos.SeatDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.seat.CreateSeatDto;
import com.example.backend.dtos.seat.GetSeatDto;
import com.example.backend.exceptions.ResourceNotfoundException;
import com.example.backend.mappers.SeatMapper;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.repositories.SeatRepository;

@Service
public class SeatService implements SeatDao {
    SeatRepository seatRepository;
    TheaterService theaterService;

    public SeatService(SeatRepository _seatRepository, TheaterService _theaterService) {
        this.seatRepository = _seatRepository;
        this.theaterService = _theaterService;
    }

    @Override
    public ResponseDto getAllSeatsByTheater(UUID theaterId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<SeatModel> seatPage = seatRepository.findSeatsByTheater(theaterId, pageable);

        Collection<SeatModel> seatList = seatPage.getContent();
        Collection<GetSeatDto> seatListDto = seatList.stream().map(seat -> SeatMapper.INSTANCE.SeatToDto(seat))
                .collect(Collectors.toList());

        ResponseDto response = ResponseDto.builder()
                .data(seatListDto)
                .pageSize(seatPage.getSize())
                .pageNumber(seatPage.getNumber())
                .totalElements(seatPage.getTotalElements())
                .totalPages(seatPage.getTotalPages())
                .isLastPage(seatPage.isLast())
                .build();

        return response;

    }

    @Override
    public SeatModel getSeat(UUID id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));

    }

    @Override
    public SeatModel createSeat(UUID theaterId, CreateSeatDto seatDto) {
        TheaterModel theater = theaterService.getTheater(theaterId);

        SeatModel seat = SeatModel.builder()
                .srow(seatDto.getSrow())
                .scolumn(seatDto.getScolumn())
                .theater(theater)
                .build();

        seatRepository.save(seat);
        return seat;

    }

    @Override
    public SeatModel deleteSeat(UUID id) {
        SeatModel seatModel = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));

        seatRepository.deleteById(id);

        return seatModel;
    }

}
