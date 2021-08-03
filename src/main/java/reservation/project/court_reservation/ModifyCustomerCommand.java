package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCustomerCommand {

    private long custId;
    @NameConstraint
    private String name;
    @Email
    private String email;
    @PhoneNumberConstraint
    private String phoneNumber;

}
