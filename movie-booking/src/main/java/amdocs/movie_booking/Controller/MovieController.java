package amdocs.movie_booking.Controller;

import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;
import amdocs.movie_booking.Dto.MovieDTO;
import amdocs.movie_booking.Dto.MovieResponseDTO;
import amdocs.movie_booking.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;
    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@Valid @RequestBody MovieDTO dto) {
        return ResponseEntity.ok(service.createMovie(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getMovie(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllMovies());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
