package reservation.reservation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import reservation.court.CourtDTO;
import reservation.court.CourtType;
import reservation.court.CreateCourtCommand;
import reservation.customer.CreateCustomerCommand;
import reservation.customer.CustomerDTO;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testCreateReservation() {

        CourtDTO testCourt = template.postForObject("/api/courts", new CreateCourtCommand(LocalTime.of(8,00), LocalTime.of(22,00),"Squash court test", CourtType.SQUASH), CourtDTO.class);
        CustomerDTO testCustomer = template.postForObject("/api/customers",new CreateCustomerCommand("John Doe","j.doe@hotmail.com","06302668743"), CustomerDTO.class);

        LocalDateTime startTime = LocalDateTime.of(2021, Month.SEPTEMBER,29,15,00);
        LocalDateTime endTime = LocalDateTime.of(2021, Month.SEPTEMBER,29,16,00);
        long customerId = testCustomer.getCustId();
        long courtId = testCourt.getCourtId();

        ReservationDTO result = template.postForObject("/api/reservations", new MakeReservationCommand(startTime,endTime,customerId,courtId),ReservationDTO.class);

        assertEquals(result.getStartTime(),startTime);
        assertEquals(result.getEndTime(),endTime);
        assertEquals(result.getCourtId(),courtId);
        //assertEquals(result.getCustId(),customerId);
    }

    @Test
    public void testModifyReservation() {
        CourtDTO testCourt = template.postForObject("/api/courts", new CreateCourtCommand(LocalTime.of(8,00), LocalTime.of(22,00),"Modify Squash court test", CourtType.SQUASH), CourtDTO.class);
        CustomerDTO testCustomer = template.postForObject("/api/customers",new CreateCustomerCommand("Jane Doe","jane.doe@gmail.com","06703425634"), CustomerDTO.class);

        LocalDateTime startTime = LocalDateTime.of(2021, Month.SEPTEMBER,20,15,00);
        LocalDateTime endTime = LocalDateTime.of(2021, Month.SEPTEMBER,20,16,00);
        long customerId = testCustomer.getCustId();
        long courtId = testCourt.getCourtId();

        ReservationDTO testEntity = template.postForObject("/api/reservations", new MakeReservationCommand(startTime,endTime,customerId,courtId),ReservationDTO.class);

        template.put("/api/reservations", new ModifyReservationCommand(testEntity.getResId(),LocalDateTime.of(2021,Month.SEPTEMBER,19,15,00),
                LocalDateTime.of(2021,Month.SEPTEMBER,19,16,00),testCustomer.getCustId(),testCourt.getCourtId()));

        ReservationDTO result = template.getForObject("/api/reservations/" + testEntity.getResId(),ReservationDTO.class);

        assertEquals(result.getStartTime(),LocalDateTime.of(2021,Month.SEPTEMBER,19,15,00));
    }

    public void testListReservationsByCourtId() {
        CourtDTO testCourt = template.postForObject("/api/courts", new CreateCourtCommand(LocalTime.of(8,00), LocalTime.of(22,00),"List reservations By courtid test", CourtType.TENNIS), CourtDTO.class);
        CustomerDTO testCustomer = template.postForObject("/api/customers",new CreateCustomerCommand("Test Attila","test@hotmail.com","06303785432"), CustomerDTO.class);

        template.postForObject("/api/reservations", new MakeReservationCommand(
                LocalDateTime.of(2021,Month.SEPTEMBER,15,10,00),
                LocalDateTime.of(2021, Month.SEPTEMBER,15,11,00),
                testCustomer.getCustId(),testCourt.getCourtId()
        ),ReservationDTO.class);
        template.postForObject("/api/reservations", new MakeReservationCommand(
                LocalDateTime.of(2021,Month.SEPTEMBER,16,10,00),
                LocalDateTime.of(2021, Month.SEPTEMBER,16,11,00),
                testCustomer.getCustId(),testCourt.getCourtId()
        ),ReservationDTO.class);

        List<ReservationDTO> result = template.exchange("/api/reservations", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ReservationDTO>>() {
                }).getBody();

        assertEquals(2,result.size());
    }

    @Test
    public void testReservationNotFound() {
        Problem result = template.getForObject("/api/reservations/99999", Problem.class);

        assertEquals(URI.create("reservations/not-found"),result.getType());
        assertEquals(Status.NOT_FOUND, result.getStatus());
    }

    @Test
    public void testReservationWithInvalidParameters() {

        CourtDTO testCourt = template.postForObject("/api/courts", new CreateCourtCommand(LocalTime.of(8,00), LocalTime.of(22,00),"Invalid res", CourtType.VOLLEYBALL), CourtDTO.class);
        CustomerDTO testCustomer = template.postForObject("/api/customers",new CreateCustomerCommand("Invalid Joe","joe.invalid@hotmail.com","06702445798"), CustomerDTO.class);

        LocalDateTime startTime = LocalDateTime.of(2021, Month.AUGUST,7,15,00);
        LocalDateTime endTime = LocalDateTime.of(2021, Month.AUGUST,7,16,00);
        long customerId = testCustomer.getCustId();
        long courtId = testCourt.getCourtId();

        Problem result = template.postForObject("/api/reservations", new MakeReservationCommand(startTime,endTime,customerId,courtId),Problem.class);

        assertEquals(URI.create("reservations/bad-request"),result.getType());
        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

}
