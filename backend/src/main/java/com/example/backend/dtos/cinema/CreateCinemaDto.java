package com.example.backend.dtos.cinema;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCinemaDto {
    public String name;
    public String location;
}
