package reservation.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.reservation.Reservation;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Schema(name = "Customer id", example = "1")
    private Long custId;
    @Schema(name = "Customer's name", example = "John Doe")
    private String name;
    @Schema(name = "Customer's email", example = "john.doe@outlook.com")
    private String email;
    @Schema(name = "Customer's phonenumber", example = "06208654123")
    private String phoneNumber;
    @Schema(name = "Customer's reservations")
    private List<Reservation> reservations;

}
