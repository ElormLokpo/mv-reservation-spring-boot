package com.example.backend.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.daos.ShowtimeDao;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.dtos.showtime.CreateShowtimeDto;
import com.example.backend.dtos.showtime.GetShowtimeDto;
import com.example.backend.exceptions.ResourceNotfoundException;
import com.example.backend.mappers.ShowtimeMapper;
import com.example.backend.models.showtime.ShowtimeModel;
import com.example.backend.models.theater.TheaterModel;
import com.example.backend.models.tickets.TicketModel;
import com.example.backend.repositories.ShowtimeRepository;
import com.example.backend.repositories.TicketRepository;

@Service
public class ShowtimeService implements ShowtimeDao {

        ShowtimeRepository showtimeRepository;
        TheaterService theaterService;
        TicketRepository ticketRepository;

        public ShowtimeService(ShowtimeRepository _showtimeRepository, TheaterService _theaterService, TicketRepository _ticketRepository) {
                showtimeRepository = _showtimeRepository;
                theaterService = _theaterService;
                ticketRepository = _ticketRepository;
        }

        @Override
        public ResponseDto getAllShowtimes(int pageNo, int pageSize, String sortBy, String sortDir) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<ShowtimeModel> showtimePage = showtimeRepository.findAll(pageable);

                Collection<ShowtimeModel> showTimes = showtimePage.getContent();
                Collection<GetShowtimeDto> showTimeList = showTimes.stream()
                                .map(showtime -> ShowtimeMapper.INSTANCE.ShowtimeToDtoModel(showtime))
                                .collect(Collectors.toList());

                ResponseDto response = ResponseDto.builder()
                                .data(showTimeList)
                                .pageNumber(showtimePage.getNumber())
                                .pageSize(showtimePage.getSize())
                                .totalElements(showtimePage.getTotalElements())
                                .totalPages(showtimePage.getTotalPages())
                                .isLastPage(showtimePage.isLast())
                                .build();

                return response;
        }

        @Override
        public ResponseDto getAllShowtimesByMovie(UUID movieId, int pageNo, int pageSize, String sortBy,
                        String sortDir) {

                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<ShowtimeModel> showtimePage = showtimeRepository.findShowtimesByMovie(movieId, pageable);

                Collection<ShowtimeModel> showTimes = showtimePage.getContent();

                Collection<GetShowtimeDto> showTimeList = showTimes.stream()
                                .map(showtime -> ShowtimeMapper.INSTANCE.ShowtimeToDtoModel(showtime))
                                .collect(Collectors.toList());

                ResponseDto response = ResponseDto.builder()
                                .data(showTimeList)
                                .pageNumber(showtimePage.getNumber())
                                .pageSize(showtimePage.getSize())
                                .totalElements(showtimePage.getTotalElements())
                                .totalPages(showtimePage.getTotalPages())
                                .isLastPage(showtimePage.isLast())
                                .build();

                return response;

        }

        @Override
        public ResponseDto getAllShowtimesByTheater(UUID theaterId, int pageNo, int pageSize, String sortBy,
                        String sortDir) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<ShowtimeModel> showtimePage = showtimeRepository.findShowTimesByTheater(theaterId, pageable);

                Collection<ShowtimeModel> showTimes = showtimePage.getContent();
                Collection<GetShowtimeDto> showTimeList = showTimes.stream()
                                .map(showtime -> ShowtimeMapper.INSTANCE.ShowtimeToDtoModel(showtime))
                                .collect(Collectors.toList());

                ResponseDto response = ResponseDto.builder()
                                .data(showTimeList)
                                .pageNumber(showtimePage.getNumber())
                                .pageSize(showtimePage.getSize())
                                .totalElements(showtimePage.getTotalElements())
                                .totalPages(showtimePage.getTotalPages())
                                .isLastPage(showtimePage.isLast())
                                .build();

                return response;
        }

        @Override
        public ShowtimeModel getShowtime(UUID id) {
                return showtimeRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));
        }

        @Override
        public ShowtimeModel createShowtime(CreateShowtimeDto showtimeDto) {
                ShowtimeModel showtime = ShowtimeMapper.INSTANCE.ShowtimeDtoToModel(showtimeDto);
                TheaterModel theaterModel = theaterService.getTheater(showtimeDto.movieTheater.theaterId);

                showtime.setAvailableSeats(theaterModel.getSeatingCapacity());
                ArrayList<TicketModel> tickets = new ArrayList<TicketModel>();

                ShowtimeModel showtimeCreated = showtimeRepository.save(showtime);

                for (int i = 1; i <= theaterModel.getSeatingCapacity(); i++) {
                        tickets.add(
                                        TicketModel.builder()
                                                        .price(showtimeDto.getRate())
                                                        .showtime(showtimeCreated)
                                                        .build());
                }

                ticketRepository.saveAll(tickets);
                return showtime;
        }

        @Override
        public ShowtimeModel deleteShowtime(UUID id) {
                ShowtimeModel showtime = showtimeRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotfoundException("Movie with id:" + id + " not found"));

                showtimeRepository.deleteById(id);
                return showtime;

        }

}
