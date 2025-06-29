package amdocs.movie_booking.services;

import amdocs.movie_booking.Dto.MovieDTO;
import amdocs.movie_booking.Dto.MovieResponseDTO;
import amdocs.movie_booking.model.Movie;

import java.util.List;
import java.util.UUID;

public interface MovieService {
    MovieResponseDTO createMovie(MovieDTO dto);
    MovieResponseDTO getMovie(UUID id);
    List<MovieResponseDTO> getAllMovies();
    void deleteMovie(UUID id);
}
