package amdocs.movie_booking.repository;

import amdocs.movie_booking.model.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id = :id")
Optional<Seat> findSeatForUpdate(@Param("id") UUID id);
//  Optional<Seat> findSeatWithoutLock(@Param("id") UUID id);
}
