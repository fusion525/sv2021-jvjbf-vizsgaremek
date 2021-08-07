package reservation.reservation;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import reservation.court.Court;
import reservation.court.CourtRepository;
import reservation.customer.Customer;
import reservation.customer.CustomerService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private ModelMapper modelMapper;
    private ReservationRepository reservationRepository;
    private CustomerService customerService;
    private CourtRepository courtRepository;

    public ReservationService(ModelMapper modelMapper, ReservationRepository reservationRepository, CustomerService customerService, CourtRepository courtRepository) {
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.courtRepository = courtRepository;
    }

    public ReservationDTO getReservationById(long id) {
        return modelMapper.map(reservationRepository.getById(id), ReservationDTO.class);
    }

    public List<ReservationDTO> getReservationByCourtId(long id) {
        Type targetListType = new TypeToken<List<ReservationDTO>>(){}.getType();

        return modelMapper.map(reservationRepository.findByCourtId(id),targetListType);

    }

    //public List<ReservationDTO> getReservationByTimeByCourt(GetReservationCommand command) {
    //    Type targetListType = new TypeToken<List<ReservationDTO>>(){}.getType();
    //
    //    return modelMapper.map(reservationRepository.findReservationByTimeByCourt(command.getCourtId(),
    //            command.getStart(),command.getEnd()),targetListType);
    //}

    public List<ReservationDTO> getActualReservations() {
        Type targetListType = new TypeToken<List<ReservationDTO>>(){}.getType();

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);

        return modelMapper.map(reservationRepository.findActualReservations(start, end), targetListType);
    }

    public ReservationDTO makeReservation(MakeReservationCommand command) {
        Court court = courtRepository.getById(command.getCourtId());
        Customer customer = customerService.getCustomerById(command.getCustomerId());
        LocalDateTime time = command.getTime();

        Reservation reservation = new Reservation(time,court,customer);
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public ReservationDTO modifyReservation(ModifyReservationCommand command) {
        Reservation reservation = reservationRepository.getById(command.getReservationId());
        Customer customer = customerService.getCustomerById(command.getCustomerId());
        Court court = courtRepository.getById(command.getCourtId());

        reservation.setCourt(court);
        reservation.setCustomer(customer);
        reservation.setTime(command.getTime());

        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public void deleteReservation(long id) {
        Reservation reservation = reservationRepository.getById(id);

        reservationRepository.delete(reservation);
    }
}
