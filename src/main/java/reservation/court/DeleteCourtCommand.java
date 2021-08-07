package reservation.court;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteCourtCommand {

    @Schema(description = "Court id", example = "1")
    private long id;

}
