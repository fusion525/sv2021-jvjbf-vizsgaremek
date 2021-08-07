package reservation.reservation;

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
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable("id") long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/court/{id}")
    public List<ReservationDTO> getReservationsByCourtId(@PathVariable("id") long id) {
        return reservationService.getReservationByCourtId(id);
    }

    //@GetMapping("/time")
    //public List<ReservationDTO> getReservationByTimeByCourt(@RequestBody GetReservationCommand command){
    //    return reservationService.getReservationByTimeByCourt(command);
    //}

    @GetMapping
    public List<ReservationDTO> getActualReservations() {
        return reservationService.getActualReservations();
    }

    @PostMapping
    public ReservationDTO makeReservation(@RequestBody @Valid MakeReservationCommand command) {
        return reservationService.makeReservation(command);
    }

    @PutMapping
    public ReservationDTO modifyReservation(@RequestBody @Valid ModifyReservationCommand command){
        return  reservationService.modifyReservation(command);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
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
