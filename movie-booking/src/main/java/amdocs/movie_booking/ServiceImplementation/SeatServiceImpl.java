package amdocs.movie_booking.ServiceImplementation;

import amdocs.movie_booking.Dto.SeatRequestDto;
import amdocs.movie_booking.Dto.SeatResponseDto;
import amdocs.movie_booking.Mapper.SeatMapper;
import amdocs.movie_booking.exceptions.ResourceNotFoundException;
import amdocs.movie_booking.model.Movie;
import amdocs.movie_booking.model.Seat;
import amdocs.movie_booking.repository.MovieRepository;
import amdocs.movie_booking.repository.SeatRepository;
import amdocs.movie_booking.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;
    private final SeatMapper seatMapper;

    @Override
    public SeatResponseDto create(SeatRequestDto dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Seat seat = seatMapper.toEntity(dto, movie);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    @Override
    public SeatResponseDto get(UUID id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
        return seatMapper.toDto(seat);
    }
}