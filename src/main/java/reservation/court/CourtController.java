package reservation.court;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courts")
@Tag(name = "Operations on courts")
public class CourtController {

    private CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    @Operation(summary = "Lists all courts")
    public List<CourtDTO> listAllCourts() {
        return courtService.listAllCourts();
    }

    @GetMapping("/{id}")
    public CourtDTO getCourtById(@PathVariable(name = "id") long id) {
        return courtService.getCourtById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new court")
    @ApiResponse(responseCode = "201", description = "court has been created")
    public CourtDTO createCourt(@RequestBody @Valid CreateCourtCommand command) {
        return courtService.createCourt(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes court by id")
    @ApiResponse(responseCode = "204", description = "court deleted successfully")
    public void deleteCourtById(@RequestBody DeleteCourtCommand command) {
        courtService.deleteCourtById(command);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(EntityNotFoundException enfe) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("courts/not-found")).withTitle("Court Not found by id").withStatus(Status.NOT_FOUND)
                        .withDetail(enfe.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleNotFound(MethodArgumentNotValidException manve) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("courts/bad-request")).withTitle("Can not create court with given parameters").withStatus(Status.BAD_REQUEST)
                        .withDetail(manve.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }



}
