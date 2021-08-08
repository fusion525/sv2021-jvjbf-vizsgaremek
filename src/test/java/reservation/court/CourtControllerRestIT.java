package reservation.court;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourtControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testCreateCourt(){

        LocalTime openHour = LocalTime.of(8,00);
        LocalTime closeHour = LocalTime.of(23,00);
        String name = "Squash court 1";
        CourtType courtType = CourtType.SQUASH;

        CourtDTO result = template.postForObject("/api/courts", new CreateCourtCommand(openHour,closeHour,name,courtType), CourtDTO.class);

        assertEquals(openHour,result.getOpenHour());
        assertEquals(closeHour, result.getCloseHour());
        assertEquals("Squash court 1", result.getCourtName());
        assertEquals(courtType, result.getCourtType());
    }

    @Test
    public void testListCourtById(){

        LocalTime openHour1 = LocalTime.of(8,00);
        LocalTime closeHour1 = LocalTime.of(23,00);
        String name1 = "Squash court 1";
        CourtType courtType1 = CourtType.SQUASH;

        LocalTime openHour2 = LocalTime.of(10,00);
        LocalTime closeHour2 = LocalTime.of(20,00);
        String name2 = "Tennis court 1";
        CourtType courtType2 = CourtType.TENNIS;

        CourtDTO testEntity1 = template.postForObject("/api/courts", new CreateCourtCommand(openHour1,closeHour1,name1,courtType1), CourtDTO.class);
        CourtDTO testEntity2 = template.postForObject("/api/courts", new CreateCourtCommand(openHour2,closeHour2,name2,courtType2), CourtDTO.class);

        CourtDTO result = template.getForObject("/api/courts/" + testEntity2.getCourtId(),CourtDTO.class);

        assertEquals(openHour2,result.getOpenHour());
        assertEquals(closeHour2, result.getCloseHour());
        assertEquals("Tennis court 1", result.getCourtName());
        assertEquals(courtType2, result.getCourtType());

    }

    /*
    @Test
    public void testDeleteCourtById() {

        LocalTime openHour = LocalTime.of(8,00);
        LocalTime closeHour = LocalTime.of(23,00);
        String name = "Squash court 1";
        CourtType courtType = CourtType.SQUASH;

        CourtDTO testEntity = template.postForObject("/api/courts", new CreateCourtCommand(openHour,closeHour,name,courtType), CourtDTO.class);

        template.delete("/api/courts", new DeleteCourtCommand(testEntity.getCourtId()), null);

        List<CourtDTO> result = template.exchange("/api/courts", HttpMethod.GET, null
                , new ParameterizedTypeReference<List<CourtDTO>>() {
                }).getBody();

        assertEquals(0,result.size());

    }
    */

    @Test
    public void testCreateCourtWithInvalidParameters() {
        Problem result = template.postForObject("/api/courts", new CreateCourtCommand(LocalTime.of(8,00),LocalTime.of(22,00),"", CourtType.SQUASH), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    public void testNotFoundById() {
        Problem result = template.getForObject("/api/courts/99999999999",Problem.class);

        assertEquals(Status.NOT_FOUND, result.getStatus());
    }

}
