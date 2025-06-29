package amdocs.movie_booking.Mapper;

import amdocs.movie_booking.Dto.MovieDTO;
import amdocs.movie_booking.Dto.MovieResponseDTO;
import amdocs.movie_booking.model.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDTO dto) {
        return Movie.builder().name(dto.getName()).seatCost(dto.getSeatCost()).build();
    }

    public static MovieResponseDTO toDTO(Movie movie) {
        return MovieResponseDTO.builder().id(movie.getId()).name(movie.getName()).seatCost(movie.getSeatCost()).build();
    }
}

