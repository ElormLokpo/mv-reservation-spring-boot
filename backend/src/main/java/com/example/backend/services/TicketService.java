package com.example.backend.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.backend.daos.TicketDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.ticket.GetTicketDto;
import com.example.backend.exceptions.ResourceNotfoundException;
import com.example.backend.mappers.TicketMapper;
import com.example.backend.models.tickets.TicketModel;
import com.example.backend.repositories.TicketRepository;

@Service
public class TicketService implements TicketDao {

    TicketRepository ticketRepository;

    public TicketService(TicketRepository _ticketRepository) {
        this.ticketRepository = _ticketRepository;
    }

    @Override
    public ResponseDto getTicketsByShowtime(UUID showtimeId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<TicketModel> ticketPage = ticketRepository.findTicketsByShowtime(showtimeId, pageable);

        Collection<TicketModel> ticketContent = ticketPage.getContent();
        Collection<GetTicketDto> ticketList = ticketContent.stream()
                .map(ticket -> TicketMapper.INSTANCE.TicketToDto(ticket)).collect(Collectors.toList());

        ResponseDto response = ResponseDto.builder()
                .data(ticketList)
                .pageSize(ticketPage.getSize())
                .pageNumber(ticketPage.getNumber())
                .totalElements(ticketPage.getTotalElements())
                .totalPages(ticketPage.getTotalPages())
                .isLastPage(ticketPage.isLast())
                .build();

        return response;
    }

    @Override
    public TicketModel getTicket(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Ticket with id:" + id + " not found"));
    }

    @Override
    public TicketModel deleteTicket(UUID id) {
        TicketModel ticket = ticketRepository.findById(id)
        .orElseThrow(() -> new ResourceNotfoundException("Ticket with id:" + id + " not found"));

        ticketRepository.deleteById(id);
        return ticket;
    }

}
