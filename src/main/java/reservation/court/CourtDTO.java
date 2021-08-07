package reservation.court;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.reservation.Reservation;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtDTO {

    private Long courtId;
    private String courtName;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeHour;
    private List<Reservation> reservations;

}
