package amdocs.movie_booking.kafkaNotification;

import amdocs.movie_booking.model.Booking;
import amdocs.movie_booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderProducer {

    private final KafkaTemplate<String, ReminderEvent> kafkaTemplate;
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void checkAndSendReminders() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        List<Booking> bookingsToNotify = bookingRepository.findAll().stream()
                .filter(booking -> {
                    LocalDateTime showTime = booking.getSeat().getTiming().withSecond(0).withNano(0);
                    LocalDateTime reminderCutoff = showTime.minusMinutes(5);
                    return now.isBefore(reminderCutoff); // current time is before showTime - 5 mins
                })
                .collect(Collectors.toList());

        System.out.println("Current Time: " + now);
        System.out.println("Sending reminders for " + bookingsToNotify.size() + " bookings.");

        for (Booking booking : bookingsToNotify) {
            ReminderEvent event = new ReminderEvent();
            event.setCustomerEmail(booking.getCustomer().getEmail());
            event.setMovieName(booking.getSeat().getMovie().getName());
            event.setShowTime(booking.getSeat().getTiming());

            System.out.println("Sending reminder: " + event);
           kafkaTemplate.send(KafkaConfig.TOPIC, event);
           System.out.println("Message Send");
        }

    }
}

