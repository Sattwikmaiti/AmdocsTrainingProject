package amdocs.movie_booking.ServiceImplementation;


import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;
import amdocs.movie_booking.Dto.MovieDTO;
import amdocs.movie_booking.Dto.MovieResponseDTO;
import amdocs.movie_booking.Mapper.CustomerMapper;
import amdocs.movie_booking.Mapper.MovieMapper;
import amdocs.movie_booking.exceptions.ResourceNotFoundException;
import amdocs.movie_booking.model.Customer;
import amdocs.movie_booking.model.Movie;
import amdocs.movie_booking.repository.MovieRepository;
import amdocs.movie_booking.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;
    @Override
    public MovieResponseDTO createMovie(MovieDTO dto) {
        Movie saved = repo.save(MovieMapper.toEntity(dto));
        return MovieMapper.toDTO(saved);
    }

    @Override
    public MovieResponseDTO getMovie(UUID id) {
        Movie customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return MovieMapper.toDTO(customer);
    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {
        return repo.findAll().stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteMovie(UUID id) {
        Movie customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        repo.delete(customer);
    }

}
