package com.example.backend.dtos.theater;

import java.util.UUID;

import com.example.backend.models.cinema.CinemaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTheaterDto {
    public UUID id;
    public String name;
    public String location;
    public Integer seatingCapacity;
    public CinemaModel cinema;
}
