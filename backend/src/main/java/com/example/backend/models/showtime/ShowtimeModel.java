package com.example.backend.models.showtime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import com.example.backend.models.tickets.TicketModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "showtimes")
@Builder
public class ShowtimeModel {

    @GeneratedValue
    @Id
    public UUID id;
    public LocalDate date;
    public LocalTime time;

    @Builder.Default
    public Double rate = 0.0;

    @Builder.Default
    public int availableSeats = 0;

    @Embedded
    public MovieTheater movieTheater;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "showtime",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<TicketModel> tickets = new ArrayList<>();

}
