package amdocs.movie_booking.repository;

import amdocs.movie_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findBySeat_Timing(LocalDateTime timing);
}
