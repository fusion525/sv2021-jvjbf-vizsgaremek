package reservation.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

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

    public CustomerDTO getCustomerById(long id) {

        return modelMapper.map(customerRepository.getById(id),CustomerDTO.class);
    }

    public List<CustomerDTO> listAllCustomers() {
        Type targetListType = new TypeToken<List<CustomerDTO>>(){}.getType();

        return modelMapper.map(customerRepository.findAll(), targetListType);
    }

    public CustomerDTO createCustomer(CreateCustomerCommand command) {
        Customer customer = new Customer(command.getName(), command.getEmail(), command.getPhoneNumber());

        customerRepository.save(customer);

        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO modifyCustomer(ModifyCustomerCommand command) {
        Customer customer = customerRepository.getById(command.getCustId());

        if (!command.getName().isEmpty() || command.getName() != null) {
            customer.setName(command.getName());
        }

        if (!command.getPhoneNumber().isEmpty() || command.getPhoneNumber() != null) {
            customer.setPhoneNumber(command.getPhoneNumber());
        }

        if (!command.getEmail().isEmpty() || command.getEmail() !=null) {
            customer.setEmail(command.getEmail());
        }

        customerRepository.save(customer);

        return modelMapper.map(customer, CustomerDTO.class);
    }

    public void deleteCustomer(DeleteCustomerCommand command) {
        customerRepository.deleteById(command.getId());
    }
}
