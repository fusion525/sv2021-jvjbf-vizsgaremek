package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCustomerCommand {

    private long custId;
    private String name;
    private String email;
    private String phoneNumber;

}
