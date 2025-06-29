package amdocs.movie_booking.Controller;

import amdocs.movie_booking.Dto.SeatRequestDto;
import amdocs.movie_booking.Dto.SeatResponseDto;
import amdocs.movie_booking.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/seat")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public SeatResponseDto create(@RequestBody SeatRequestDto dto) {
        return seatService.create(dto);
    }

    @GetMapping("/{id}")
    public SeatResponseDto get(@PathVariable UUID id) {
        return seatService.get(id);
    }
}