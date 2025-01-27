package com.example.backend.dtos.cinema;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCinemaDto {
    public UUID id;
    public String name;
    public String location;
}
