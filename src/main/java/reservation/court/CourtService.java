package reservation.court;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

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

    public CourtDTO getCourtById(long id) {
        return modelMapper.map(courtRepository.getById(id), CourtDTO.class);
    }

    public List<CourtDTO> listAllCourts() {
        Type targetListType = new TypeToken<List<CourtDTO>>(){}.getType();
        return modelMapper.map(courtRepository.findAll(), targetListType);
    }

    public CourtDTO createCourt(CreateCourtCommand command) {
        Court court = new Court(command.getOpenHour(), command.getCloseHour());
        courtRepository.save(court);

        return modelMapper.map(court, CourtDTO.class);
    }

    public void deleteCourtById(long id) {
        courtRepository.deleteById(id);
    }
}
