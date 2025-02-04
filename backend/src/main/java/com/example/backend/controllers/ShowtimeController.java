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

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/showtime")
@RestController
public class ShowtimeController {
    ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService _showtimeService) {
        this.showtimeService = _showtimeService;
    }

    @GetMapping("all")
    public ResponseEntity<ResponseGen> getAllShowtimes(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto responseDto = showtimeService.getAllShowtimes(pageNo, pageSize, sortBy, sortDir);
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
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
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
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
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
        ShowtimeModel showtime = showtimeService.getShowtime(id).orElse(null);
        ResponseGen response = ResponseGen.builder().build();

        if (showtime != null) {
            response.setSuccess(true);
            response.setMessage("Showtime query successful");
            response.setData(showtime);

            return ResponseEntity.ok(response);
        }

        response.setSuccess(false);
        response.setMessage("Showtime not found");
        response.setData(showtime);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteShowtime(@PathVariable UUID id) {
        ShowtimeModel showtime = showtimeService.deleteShowtime(id);
        ResponseGen response = ResponseGen.builder().build();

        if (showtime != null) {
            response.setSuccess(true);
            response.setMessage("Showtime delete successful");
            response.setData(showtime);

            return ResponseEntity.ok(response);
        }

        response.setSuccess(false);
        response.setMessage("Showtime not found");
        response.setData(showtime);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
