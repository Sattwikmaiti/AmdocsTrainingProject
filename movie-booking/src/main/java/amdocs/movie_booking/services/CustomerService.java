package amdocs.movie_booking.services;

import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    CustomerResponseDTO getCustomer(UUID id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO dto);
    void deleteCustomer(UUID id);
}