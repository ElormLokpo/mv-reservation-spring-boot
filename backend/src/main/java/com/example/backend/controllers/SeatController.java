package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.seat.CreateSeatDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.services.SeatService;
import com.example.backend.utils.ResponseGen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/seat")
@RestController
public class SeatController {

    SeatService seatService;

    public SeatController(SeatService _seatService) {
        this.seatService = _seatService;
    }

    @GetMapping("theater")
    public ResponseEntity<ResponseGen> getAllSeatsByTheater(
            @RequestParam(value = "theaterId", required = true) UUID theaterId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "srow", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        ResponseDto responseDto = seatService.getAllSeatsByTheater(theaterId, pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(responseDto);

        response.setSuccess(true);
        response.setMessage("Seat Query successful");

        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseGen> getSeat(@PathVariable UUID id) {
        SeatModel seat = seatService.getSeat(id);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Seat query successful")
                .data(seat)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("{theaterId}")
    public ResponseEntity<ResponseGen> createSeat(@PathVariable UUID theaterId, @RequestBody CreateSeatDto seatDto) {
        SeatModel seat = seatService.createSeat(theaterId, seatDto);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Seat creation successful")
                .data(seat)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteSeat(@PathVariable UUID id) {
        SeatModel seat = seatService.deleteSeat(id);

        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Seat delete successful")
        .data(seat)
        .build();
        return ResponseEntity.ok(response);
    }

}
