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
import java.util.UUID;
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

    @GetMapping("cinema")
    public ResponseEntity<ResponseGen> getAllTheatersByCinema(
            @RequestParam(value = "cinemaId", required = true) UUID cinemaId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto resposneDto = theaterService.getAllTheatersByCinema(cinemaId, pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(resposneDto);

        response.setSuccess(true);
        response.setMessage("Theater query successful");

        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseGen> getAllTheaters(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto resposneDto = theaterService.getAllTheaters(pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(resposneDto);

        response.setSuccess(true);
        response.setMessage("Theater query successful");

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseGen> getTheater(@PathVariable UUID id) {
        TheaterModel theater = theaterService.getTheater(id);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Theater query successful")
                .data(theater)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("{cinemaId}")
    public ResponseEntity<ResponseGen> createTheater(
            @PathVariable UUID cinemaId,
            @RequestBody CreateTheaterDto theaterDto) {
        TheaterModel theaterModel = theaterService.createTheater(cinemaId, theaterDto);

        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Theater creation successful")
                .data(theaterModel)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteTheater(@PathVariable UUID id) {
        TheaterModel theater = theaterService.deleteTheater(id);
        ResponseGen response = ResponseGen.builder()
        .success(true)
        .message("Theater deleted successfully")
        .data(theater)
        .build();

        return ResponseEntity.ok(response);

    }

}
