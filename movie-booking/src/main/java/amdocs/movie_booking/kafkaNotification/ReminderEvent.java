package amdocs.movie_booking.kafkaNotification;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEvent {
    private String customerEmail;
    private String movieName;
    private LocalDateTime showTime;
}
