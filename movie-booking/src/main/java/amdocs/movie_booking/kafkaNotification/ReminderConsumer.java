package amdocs.movie_booking.kafkaNotification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReminderConsumer {

    @KafkaListener(topics = KafkaConfig.TOPIC, groupId = "reminder-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(ReminderEvent event) {
        System.out.printf("Email sent to %s for movie %s at %s%n",
                event.getCustomerEmail(),
                event.getMovieName(),
                event.getShowTime()
        );
    }
}
