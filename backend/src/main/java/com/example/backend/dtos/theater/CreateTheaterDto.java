package com.example.backend.dtos.theater;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateTheaterDto {
    public String name;
    public String location;
    public Integer seatingCapacity;
}
