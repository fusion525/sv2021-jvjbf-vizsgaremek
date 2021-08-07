package reservation.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteCustomerCommand {

    @Schema(description = "Customer's ID", example = "1")
    private long id;

}
