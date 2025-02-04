package com.example.backend.models.tickets;

import java.sql.Time;
import java.util.UUID;

import com.example.backend.models.showtime.ShowtimeModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tickets")
@Builder
public class TicketModel {

    @GeneratedValue
    @Id
    public UUID id;
    public Double price;
    public Time issuedTime;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    public TicketStateEnum ticketState = TicketStateEnum.Pending;

    @OneToOne
    @JoinColumn(name = "showtime_id")
    public ShowtimeModel showtime;
}
