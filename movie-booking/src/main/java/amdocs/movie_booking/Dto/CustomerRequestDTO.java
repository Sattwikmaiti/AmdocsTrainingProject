package amdocs.movie_booking.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}