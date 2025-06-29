package amdocs.movie_booking.Dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SeatRequestDto {
    private UUID movieId;
   private int seatAvailability;
    private LocalDateTime timing;
}
