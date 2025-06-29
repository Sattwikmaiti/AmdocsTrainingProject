package amdocs.movie_booking.Mapper;


import amdocs.movie_booking.Dto.SeatRequestDto;
import amdocs.movie_booking.Dto.SeatResponseDto;
import amdocs.movie_booking.model.Movie;
import amdocs.movie_booking.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public Seat toEntity(SeatRequestDto dto, Movie movie) {
        return Seat.builder()
                .movie(movie)
                .seatAvailability(dto.getSeatAvailability())
                .timing(dto.getTiming())
                .build();
    }

    public SeatResponseDto toDto(Seat seat) {
        SeatResponseDto dto = new SeatResponseDto();
        dto.setId(seat.getId());
        dto.setMovieId(seat.getMovie().getId());
        dto.setSeatAvailability(seat.getSeatAvailability());
        dto.setTiming(seat.getTiming());
        return dto;
    }
}