package reservation.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testCreateCustomer() {
        String name = "John Doe";
        String email = "john.doe@outlook.com";
        String phoneNumber = "06208665321";

        CustomerDTO result = template.postForObject("/api/customers",new CreateCustomerCommand(name,email,phoneNumber), CustomerDTO.class);

        assertEquals(name,result.getName());
        assertEquals(email,result.getEmail());
        assertEquals(phoneNumber,result.getPhoneNumber());
        assertEquals(null, result.getReservations());
    }

    @Test
    public void testModifyCustomer() {
        String name = "John Doe";
        String email = "john.doe@outlook.com";
        String phoneNumber = "06208665321";

        String nameModified = "Jack Doe";

        CustomerDTO customer = template.postForObject("/api/customers",new CreateCustomerCommand(name,email,phoneNumber), CustomerDTO.class);
        template.put("/api/customers", new ModifyCustomerCommand(customer.getCustId(), nameModified,email,phoneNumber));

        CustomerDTO modifiedCustomer = template.getForObject("/api/customers/" + customer.getCustId(),CustomerDTO.class);

        assertEquals("Jack Doe", modifiedCustomer.getName());

    }

    @Test
    public void testCreateCustomerWithInvalidParameters() {
        Problem result = template.postForObject("/api/customers", new CreateCustomerCommand("","12","jack.doe@outlook.com"), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    public void testCustomerNotFoundById(){
        Problem result = template.getForObject("/api/customers/99999999999",Problem.class);

        assertEquals(Status.NOT_FOUND, result.getStatus());
    }

}
