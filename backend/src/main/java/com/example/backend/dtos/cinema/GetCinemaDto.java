package com.example.backend.dtos.cinema;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GetCinemaDto {
    public UUID id;
    public String name;
    public String location;
}
