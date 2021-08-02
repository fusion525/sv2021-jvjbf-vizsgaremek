package reservation.project.court_reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    private CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public List<CourtDTO> listAllCourts() {
        return courtService.listAllCourts();
    }

    @GetMapping("/{id}")
    public CourtDTO getCourtById(@PathVariable(name = "id") long id) {
        return courtService.getCourtById(id);
    }

    @PostMapping
    public CourtDTO createCourt(CreateCourtCommand command) {
        return courtService.createCourt(command);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourtById(@PathVariable(name = "id") long id) {
        courtService.deleteCourtById(id);
    }

}
