package amdocs.movie_booking.Mapper;


import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;
import amdocs.movie_booking.model.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .verified(false)
                .build();
    }

    public static CustomerResponseDTO toDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .verified(customer.isVerified())
                .build();
    }
}