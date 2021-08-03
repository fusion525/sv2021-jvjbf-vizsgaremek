package reservation.project.court_reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtDTO {

    private Long courtId;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeHour;
    private List<Reservation> reservations;

}
