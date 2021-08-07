package reservation.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.constraints.NameConstraint;
import reservation.constraints.PhoneNumberConstraint;

import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {

    @NameConstraint
    private String name;
    @Email
    private String email;
    @PhoneNumberConstraint
    private String phoneNumber;

}
