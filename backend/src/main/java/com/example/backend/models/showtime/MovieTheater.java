package com.example.backend.models.showtime;

import java.util.UUID;

import lombok.Data;

@Data
public class MovieTheater {
    public UUID movieId;
    public UUID theaterId;
}
