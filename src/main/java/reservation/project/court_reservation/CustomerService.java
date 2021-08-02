package reservation.project.court_reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;
    private CustomerRepository customerRepository;

    public CustomerService(ModelMapper modelMapper, ObjectMapper objectMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(long id) {
        return customerRepository.getById(id);
    }

}
