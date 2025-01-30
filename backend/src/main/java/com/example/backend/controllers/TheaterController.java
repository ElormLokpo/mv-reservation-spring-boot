package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.theater.CreateTheaterDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.services.TheaterService;
import com.example.backend.utils.ResponseGen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/theater")
@RestController
public class TheaterController {

    TheaterService theaterService;

    public TheaterController(TheaterService _theaterService) {
        this.theaterService = _theaterService;
    }

    @GetMapping
    public ResponseEntity<ResponseGen> getAllTheaters(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto resposneDto = theaterService.getAllTheaters(pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(resposneDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseGen> getTheater(@PathVariable UUID id) {
        Optional<TheaterModel> theaterFound = theaterService.getTheater(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Theater query successful")
                .data(theaterFound)
                .build();

        ResponseGen notFoundResponse = ResponseGen.builder()
                .success(false)
                .message("Theater not found")
                .data(null)
                .build();

        return theaterFound.map(theatR -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(notFoundResponse));
    }


    @PostMapping  
    public ResponseEntity<ResponseGen> createTheater(@RequestBody CreateTheaterDto theaterDto){
        TheaterModel theaterModel = theaterService.createTheater(theaterDto);

        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Theater creation successful")
        .data(theaterModel)
        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteTheater(@PathVariable UUID id){
        TheaterModel theaterModel = theaterService.deleteTheater(id);

        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Theater delete successful")
        .data(theaterModel)
        .build();

        return ResponseEntity.ok(response);
    }

}
