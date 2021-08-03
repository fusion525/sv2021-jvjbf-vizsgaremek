package reservation.project.court_reservation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }

}
