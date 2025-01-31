package com.example.backend.models.cinema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.theater.TheaterModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    public String name;
    public String location;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, orphanRemoval = true)
    public Collection<TheaterModel> theaters = new ArrayList<>();

}
