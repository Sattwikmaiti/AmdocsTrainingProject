package amdocs.movie_booking.ServiceImplementation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import amdocs.movie_booking.Dto.CustomerRequestDTO;
import amdocs.movie_booking.Dto.CustomerResponseDTO;
import amdocs.movie_booking.Mapper.CustomerMapper;
import amdocs.movie_booking.exceptions.ResourceNotFoundException;
import amdocs.movie_booking.model.Customer;
import amdocs.movie_booking.repository.CustomerRepository;
import amdocs.movie_booking.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer saved = repo.save(CustomerMapper.toEntity(dto));
        return CustomerMapper.toDTO(saved);
    }

    @Override
    public CustomerResponseDTO getCustomer(UUID id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return repo.findAll().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO dto) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return CustomerMapper.toDTO(repo.save(customer));
    }

    @Override
    public void deleteCustomer(UUID id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        repo.delete(customer);
    }
}
