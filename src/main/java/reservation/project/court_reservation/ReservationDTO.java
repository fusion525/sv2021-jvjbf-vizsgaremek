package reservation.project.court_reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("customer")
public class ReservationDTO {

    private Long resId;
    private LocalDateTime time;
    private Court court;
    private Customer customer;

}
