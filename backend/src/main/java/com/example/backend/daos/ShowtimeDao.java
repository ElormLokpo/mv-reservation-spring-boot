package com.example.backend.daos;

import java.util.Optional;
import java.util.UUID;

import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.showtime.CreateShowtimeDto;
import com.example.backend.models.showtime.ShowtimeModel;

public interface ShowtimeDao {
    public ResponseDto getAllShowtimes(int pageNo, int pageSize, String sortBy, String sortDir);

    public ResponseDto getAllShowtimeByMovie(UUID movieId, int pageNo, int pageSize, String sortBy, String sortDir);

    public ResponseDto getAllShowtimeByTheater(UUID theaterId, int pageNo, int pageSize, String sortBy, String sortDir);

    public Optional<ShowtimeModel> getShowtime(UUID id);

    public ShowtimeModel createShowtime(CreateShowtimeDto showtimeDto);

    public ShowtimeModel deleteShowtime(UUID id);
}
