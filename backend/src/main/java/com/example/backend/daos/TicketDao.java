package com.example.backend.daos;

import java.util.UUID;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.models.tickets.TicketModel;

public interface TicketDao {
    public ResponseDto getTicketsByShowtime(UUID showtimeId, int pageNo, int pageSize, String sortBy, String sortDir);

    public TicketModel getTicket(UUID id);

    public TicketModel deleteTicket(UUID id);
}
