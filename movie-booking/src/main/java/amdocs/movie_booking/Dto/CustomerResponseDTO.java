package amdocs.movie_booking.Dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private boolean verified;
}