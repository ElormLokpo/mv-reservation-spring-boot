package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.services.CinemaService;
import com.example.backend.utils.ResponseGen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/cinema")
@RestController
public class CinemaController {

    CinemaService cinemaService;

    public CinemaController(CinemaService _cinemaService) {
        this.cinemaService = _cinemaService;
    }

    @GetMapping
    public ResponseEntity<ResponseGen> getAllCinema() {
        Collection<GetCinemaDto> allCinema = cinemaService.getAllCinema();

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Cinema query successful")
                .data(allCinema)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseGen> getCinema(@PathVariable UUID id) {
        Optional<CinemaModel> cinema = cinemaService.getCinema(id);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Cinema query successful")
                .data(cinema)
                .build();

        ResponseGen notFoundResponse = ResponseGen.builder()
                .success(false)
                .message("Cinema not found")
                .data(null)
                .build();

        return cinema.map(cinemaS -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(notFoundResponse)
                );     
    }

    @PostMapping  
    public ResponseEntity<ResponseGen> createCinema(CreateCinemaDto cinemaDto){
        Boolean createdCinema = cinemaService.createCinema(cinemaDto);

        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Cinema created successfully")
        .data(null)
        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<ResponseGen> deleteCinema(@PathVariable UUID id){
        Boolean deletedCinema = cinemaService.deleteCinema(id);

        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Cinema deleted successfully")
        .data(null)
        .build();

        return ResponseEntity.ok(response);
    }

}
