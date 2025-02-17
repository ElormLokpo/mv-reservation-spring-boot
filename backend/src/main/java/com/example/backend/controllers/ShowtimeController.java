package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.showtime.ShowtimeModel;
import com.example.backend.services.ShowtimeService;
import com.example.backend.utils.ResponseGen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;
import com.example.backend.dtos.showtime.CreateShowtimeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/showtime")
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
public class ShowtimeController {
    ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService _showtimeService) {
        this.showtimeService = _showtimeService;
    }

    @GetMapping("all")
    public ResponseEntity<ResponseGen> getAllShowtimes(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto responseDto = showtimeService.getAllShowtimes(pageNo, pageSize,
                sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(responseDto);

        response.setSuccess(true);
        response.setMessage("Showtime query successful");

        return ResponseEntity.ok(response);

    }

    @GetMapping("movie")
    public ResponseEntity<ResponseGen> getAllShowtimesByMovie(
            @RequestParam(value = "movieId", required = true) UUID movieId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto responseDto = showtimeService.getAllShowtimesByMovie(movieId, pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(responseDto);

        response.setSuccess(true);
        response.setMessage("Showtime query successful");

        return ResponseEntity.ok(response);

    }

    @GetMapping("theater")
    public ResponseEntity<ResponseGen> getAllShowtimesByTheater(
            @RequestParam(value = "theaterId", required = true) UUID theaterId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto responseDto = showtimeService.getAllShowtimesByTheater(theaterId, pageNo, pageSize, sortBy,
                sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(responseDto);

        response.setSuccess(true);
        response.setMessage("Showtime query successful");

        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseGen> getShowtime(@PathVariable UUID id) {
        ShowtimeModel showtime = showtimeService.getShowtime(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Showtime query successful")
                .data(showtime)
                .build();

        return ResponseEntity.ok(response);

    }
    
    @PostMapping
    public ResponseEntity<ResponseGen> createSthowtime(@RequestBody CreateShowtimeDto showtimeDto) {
        ShowtimeModel showtime = showtimeService.createShowtime(showtimeDto);

        // handle showtime conflict checks...same two diff movies same theater...same
        // time

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Showtime created successfully")
                .data(showtime)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteShowtime(@PathVariable UUID id) {
        ShowtimeModel showtime = showtimeService.deleteShowtime(id);
        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Showtime deletion successful")
        .data(showtime)
        .build();

        return ResponseEntity.ok(response);
    }

}
