package reservation.reservation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Operations on reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Lists reservation by id")
    public ReservationDTO getReservationById(@PathVariable("id") long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/court/{id}")
    @Operation(description = "Lists Reservations ny court IDs")
    public List<ReservationDTO> getReservationsByCourtId(@PathVariable("id") long id) {
        return reservationService.getReservationByCourtId(id);
    }

    //@GetMapping("/time")
    //public List<ReservationDTO> getReservationByTimeByCourt(@RequestBody GetReservationCommand command){
    //    return reservationService.getReservationByTimeByCourt(command);
    //}

    @GetMapping
    @Operation(description = "Lists reservations on actual week")
    public List<ReservationDTO> getActualReservations() {
        return reservationService.getActualReservations();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Makes reservation")
    @ApiResponse(responseCode = "201", description = "Reservation created successfully")
    public ReservationDTO makeReservation(@RequestBody @Valid MakeReservationCommand command) {
        return reservationService.makeReservation(command);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(description = "Modifies reservation")
    @ApiResponse(responseCode = "202", description = "Reservation modified")
    public ReservationDTO modifyReservation(@RequestBody @Valid ModifyReservationCommand command){
        return  reservationService.modifyReservation(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Deletes reservation by id")
    @ApiResponse(responseCode = "204", description = "Reservation deleted")
    public void deleteReservation(@RequestBody DeleteReservationCommand command) {
        reservationService.deleteReservation(command);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(EntityNotFoundException enfe) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("reservations/not-found")).withTitle("Reservation Not found by id").withStatus(Status.NOT_FOUND)
                        .withDetail(enfe.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleNotFound(MethodArgumentNotValidException manve) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("reservations/bad-request")).withTitle("Can not make reservation with given parameters").withStatus(Status.BAD_REQUEST)
                        .withDetail(manve.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
