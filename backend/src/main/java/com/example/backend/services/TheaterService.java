package com.example.backend.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.backend.daos.TheaterDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.repositories.TheaterRepository;

public class TheaterService implements TheaterDao {

    TheaterRepository theaterReqRepository;

    public TheaterService(TheaterRepository _theaterRepository){
        this.theaterReqRepository = _theaterRepository;
    }

    @Override
    public ResponseDto getAllTheaters(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<TheaterModel> theaterPage =  theaterReqRepository.findAll(pageable);

        Collection<TheaterModel> theaterList = theaterPage.getContent();
        

    }

    @Override
    public Optional<TheaterModel> getTheater(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTheater'");
    }

    @Override
    public TheaterModel createTheater(CreateTheaterDto theaterDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTheater'");
    }

    @Override
    public TheaterModel deleteTheater(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTheater'");
    }

}
