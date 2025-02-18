package com.example.backend.models.seats;

import java.util.UUID;
import com.example.backend.models.theater.TheaterModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "seats")
@Builder
public class SeatModel {
    @GeneratedValue
    @Id
    public UUID id;
    public Integer slabel;
   
    @Builder.Default
    @Enumerated(EnumType.STRING)
    public SeatStatusEnum status = SeatStatusEnum.Available;

    // Theater rel
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="theater_id")
    public TheaterModel theater;

}
