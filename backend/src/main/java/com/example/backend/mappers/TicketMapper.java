package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.backend.dtos.ticket.CreateTicketDto;
import com.example.backend.dtos.ticket.GetTicketDto;
import com.example.backend.models.tickets.TicketModel;

@Mapper
public interface TicketMapper {
    public TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    GetTicketDto TicketToDto(TicketModel ticket);
    TicketModel TicketDtoToModel(CreateTicketDto ticketDto);
}
