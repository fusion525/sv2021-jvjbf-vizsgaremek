package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long custId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Reservation> reservations;

}
