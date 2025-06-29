package amdocs.movie_booking.services;

import amdocs.movie_booking.Dto.SeatRequestDto;
import amdocs.movie_booking.Dto.SeatResponseDto;

import java.util.UUID;

public interface SeatService {
    SeatResponseDto create(SeatRequestDto dto);
    SeatResponseDto get(UUID id);
}