package amdocs.movie_booking.Controller;

import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;
import amdocs.movie_booking.exceptions.ResourceNotFoundException;
import amdocs.movie_booking.model.Booking;
import amdocs.movie_booking.model.Customer;
import amdocs.movie_booking.model.Seat;
import amdocs.movie_booking.repository.BookingRepository;
import amdocs.movie_booking.repository.CustomerRepository;
import amdocs.movie_booking.repository.SeatRepository;
import amdocs.movie_booking.services.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(service.createCustomer(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(service.updateCustomer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


 @Transactional
    @PostMapping("/book")
    public ResponseEntity<String> bookSeat(@RequestParam UUID customerId,
                                           @RequestParam UUID seatId) {
        try {
            // 1. Fetch and validate customer
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            if (!customer.isVerified()) {
                log.warn("❌ [{}] Booking FAILED - Unverified customer: {}", LocalDateTime.now(), customer.getEmail());
                throw new IllegalStateException("Customer email not verified");
            }

            // 2. Lock and fetch the seat
            Seat seat = seatRepository.findSeatForUpdate(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

            // 3. Check availability
            if (seat.getSeatAvailability() <= 0) {
                log.warn("❌ [{}] Booking FAILED - No seats left for customer: {}", LocalDateTime.now(), customer.getEmail());
                throw new IllegalStateException("No seats available");
            }

            // 4. Decrease availability
            seat.setSeatAvailability(seat.getSeatAvailability() - 1);
            seatRepository.save(seat);

            // 5. Create booking
            Booking booking = Booking.builder()
                    .customer(customer)
                    .seat(seat)
                    .seatType(0) // or set this to the appropriate type if needed
                    .build();
            bookingRepository.save(booking);

            log.info("✅ [{}] Booking SUCCESSFUL - Customer: {}, Seat ID: {}, Remaining: {}",
                    LocalDateTime.now(), customer.getEmail(), seatId, seat.getSeatAvailability());

            return ResponseEntity.ok("Booking successful");

        } catch (Exception e) {
            log.error("❌ [{}] Booking ERROR - CustomerId: {}, SeatId: {}, Reason: {}",
                    LocalDateTime.now(), customerId, seatId, e.getMessage());
            throw e;
        }
    }


}
