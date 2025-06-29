package amdocs.movie_booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int seatAvailability;

    @ManyToOne
    private Movie movie;

//    @ElementCollection
//    private List<Integer> remainingSeats;

    private LocalDateTime timing;
}
