package com.example.backend.dtos.ticket;

import java.util.UUID;
import com.example.backend.models.showtime.ShowtimeModel;
import com.example.backend.models.tickets.TicketStateEnum;

public class GetTicketDto {
    public UUID id;
    public Double price;

    public TicketStateEnum ticketState = TicketStateEnum.Pending;
    
    public ShowtimeModel showtime;
}
