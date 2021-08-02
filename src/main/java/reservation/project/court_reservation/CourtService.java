package reservation.project.court_reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CourtService {

    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;
    private CourtRepository courtRepository;

    public CourtService(ModelMapper modelMapper, ObjectMapper objectMapper, CourtRepository courtRepository) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.courtRepository = courtRepository;
    }

    public Court getCourtById(long id) {
        return courtRepository.getById(id);
    }
}
