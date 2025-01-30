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
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.dtos.theater.GetTheaterDto;
import com.example.backend.mappers.TheaterMapper;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.repositories.TheaterRepository;

@Service
public class TheaterService implements TheaterDao {

    TheaterRepository theaterReqRepository;

    public TheaterService(TheaterRepository _theaterRepository) {
        this.theaterReqRepository = _theaterRepository;
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
    public TheaterModel createTheater(CreateTheaterDto theaterDto) {
        return theaterReqRepository.save(
                TheaterMapper.INSTANCE.theaterDtoToModel(theaterDto));
    }

    @Override
    public TheaterModel deleteTheater(UUID id) {
        Boolean theaterExists = theaterReqRepository.existsById(id);
        TheaterModel theater = theaterReqRepository.findById(id).orElse(null);

        if(theaterExists){
            theaterReqRepository.deleteById(id);
        }

        return theater;
    }

}
