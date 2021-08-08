package reservation.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.constraints.NameConstraint;
import reservation.constraints.PhoneNumberConstraint;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCustomerCommand {

    @Schema(name = "Customer id", example = "1")
    private long custId;
    @NameConstraint
    @Schema(name = "Customer's name", example = "Jack Doe")
    private String name;
    @Email
    @Schema(name = "Customer's email", example = "jack.doe@outlook.com")
    private String email;
    @PhoneNumberConstraint
    @Schema(name = "Customer's phonenumber", example = "06208654123")
    private String phoneNumber;

    public ModifyCustomerCommand(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
