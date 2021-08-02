package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourtCommand {

    private LocalDate openHour;
    private LocalDate closeHour;

}
