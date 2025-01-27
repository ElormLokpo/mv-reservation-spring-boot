package com.example.backend.models.cinema;

import java.util.Collection;
import java.util.UUID;

import com.example.backend.models.theater.TheaterModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cinema")
@Builder
public class CinemaModel {
    @GeneratedValue
    @Id
    public UUID id;

    @NotEmpty(message="Name of cinema is required")
    public String name;
    public String location;

    @OneToMany(mappedBy="cinema")
    public Collection<TheaterModel> theaters;

}
