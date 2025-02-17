package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.seats.SeatModel;
import com.example.backend.services.SeatService;
import com.example.backend.utils.ResponseGen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/seat")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class SeatController {

    SeatService seatService;

    public SeatController(SeatService _seatService) {
        this.seatService = _seatService;
    }

    @GetMapping("theater")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('clerk:read')")
    public ResponseEntity<ResponseGen> getAllSeatsByTheater(
            @RequestParam(value = "theaterId", required = true) UUID theaterId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "slabel", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        ResponseDto responseDto = seatService.getAllSeatsByTheater(theaterId, pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(responseDto);

        response.setSuccess(true);
        response.setMessage("Seat Query successful");

        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('clerk:read')")
    public ResponseEntity<ResponseGen> getSeat(@PathVariable UUID id) {
        SeatModel seat = seatService.getSeat(id);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Seat query successful")
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
