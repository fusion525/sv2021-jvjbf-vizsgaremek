package reservation.project.court_reservation;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/court")
    public List<ReservationDTO> getReservationsByCourtId(@RequestBody GetReservationCommand command) {
        return reservationService.getReservationByCourtId(command);
    }

    @GetMapping("/time")
    public List<ReservationDTO> getReservationByTimeByCourt(@RequestBody GetReservationCommand command){
        return reservationService.getReservationByTimeByCourt(command);
    }

    @GetMapping
    public List<ReservationDTO> getActualReservations() {
        return reservationService.getActualReservations();
    }

    @PostMapping
    public ReservationDTO makeReservation(@RequestBody MakeReservationCommand command) {
        return reservationService.makeReservation(command);
    }

    @PutMapping
    public ReservationDTO modifyReservation(@RequestBody ModifyReservationCommand command){
        return  reservationService.modifyReservation(command);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }

}
