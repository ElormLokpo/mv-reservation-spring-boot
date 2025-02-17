package com.example.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.mappers.ResponseMapper;
import com.example.backend.models.tickets.TicketModel;
import com.example.backend.services.TicketService;
import com.example.backend.utils.ResponseGen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/api/ticket")
@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('CLERK')")
public class TicketController {
    TicketService ticketService;

    public TicketController(TicketService _ticketService) {
        this.ticketService = _ticketService;
    }

    @GetMapping("showtime")
    public ResponseEntity<ResponseGen> getTicketsByShowtime(
            @RequestParam(value = "showtimeId", required = true) UUID showtimeId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ResponseDto resposneDto = ticketService.getTicketsByShowtime(showtimeId, pageNo, pageSize, sortBy, sortDir);
        ResponseGen response = ResponseMapper.INSTANCE.responseDtotoResMapper(resposneDto);

        response.setSuccess(true);
        response.setMessage("Ticket query successful");

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseGen> getTicket(@PathVariable UUID id) {
        TicketModel ticket = ticketService.getTicket(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Ticket query successful")
                .data(ticket)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("buy")
    public ResponseEntity<ResponseGen> buyTicket(
            @RequestParam(value = "qty", defaultValue = "1", required = false) int quantity) {

                Collection<TicketModel> ticketsBought = ticketService.buyTicket(quantity);

                ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Tickets bought")
                .data(ticketsBought)
                .build();
                
                return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseGen> deleteTicket(@PathVariable UUID id) {
        TicketModel ticket = ticketService.deleteTicket(id);
        ResponseGen response = ResponseGen.builder()
                .success(true)
                .message("Ticket delete successful")
                .data(ticket)
                .build();

        return ResponseEntity.ok(response);

    }
}
