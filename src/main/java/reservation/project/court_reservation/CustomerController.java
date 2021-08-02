package reservation.project.court_reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> listAllCustomers() {
        return customerService.listAllCustomers();
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CreateCustomerCommand command) {
        return customerService.createCustomer(command);
    }

    @PutMapping
    public CustomerDTO modifyCustomer(@RequestBody ModifyCustomerCommand command) {
        return customerService.modifyCustomer(command);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
    }

}
