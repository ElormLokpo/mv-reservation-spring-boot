package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.cinema.CreateCinemaDto;
import com.example.backend.dtos.cinema.GetCinemaDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.cinema.CinemaModel;
import com.example.backend.services.CinemaService;
import com.example.backend.utils.ResponseGen;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RequestMapping("/api/cinema")
@RestController
public class CinemaController {

        CinemaService cinemaService;

        public CinemaController(CinemaService _cinemaService) {
                this.cinemaService = _cinemaService;
        }

        @GetMapping
        public ResponseEntity<ResponseGen> getAllCinema(
                        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                        @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

        ) {
                ResponseDto allCinemaResponse = cinemaService.getAllCinema(pageNo, pageSize, sortBy, sortDir);
                ResponseGen responseFromDto = ResponseMapper.INSTANCE.responseDtotoResMapper(allCinemaResponse);

                responseFromDto.setSuccess(true);
                responseFromDto.setMessage("Cinema query successful");

                return ResponseEntity.ok(responseFromDto);
        }

        @GetMapping(path = "{id}")
        public ResponseEntity<ResponseGen> getCinema(@PathVariable UUID id) {
                CinemaModel cinema = cinemaService.getCinema(id).orElse(null);

                ResponseGen response = ResponseGen.builder().build();

                if (cinema != null) {
                        response.setSuccess(true);
                        response.setMessage("Cinema query successful");
                        response.setData(cinema);

                        return ResponseEntity.ok(response);
                }

                response.setSuccess(false);
                response.setMessage("Cinema not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        @PostMapping
        public ResponseEntity<ResponseGen> createCinema(@Valid @RequestBody CreateCinemaDto cinemaDto) {
                CinemaModel createdCinema = cinemaService.createCinema(cinemaDto);

                ResponseGen response = ResponseGen.builder()
                                .success(true)
                                .message("Cinema created successfully")
                                .data(createdCinema)
                                .build();

                return ResponseEntity.ok(response);
        }

        @DeleteMapping(path = "{id}")
        public ResponseEntity<ResponseGen> deleteCinema(@PathVariable UUID id) {
                CinemaModel deletedCinema = cinemaService.deleteCinema(id);

                ResponseGen response = ResponseGen.builder()
                                .success(true)
                                .message("Cinema deleted successfully")
                                .data(deletedCinema)
                                .build();

                return ResponseEntity.ok(response);
        }

}
