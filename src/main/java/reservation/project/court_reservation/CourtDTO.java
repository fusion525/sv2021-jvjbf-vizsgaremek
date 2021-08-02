package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtDTO {

    private Long courtId;
    private LocalDate openHour;
    private LocalDate closeHour;
    private List<Reservation> reservations;

}
