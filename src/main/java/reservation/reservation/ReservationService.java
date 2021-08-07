package reservation.reservation;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import reservation.court.Court;
import reservation.court.CourtRepository;
import reservation.customer.Customer;
import reservation.customer.CustomerService;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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

        LocalDateTime start = LocalDateTime.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),0,0);
        LocalDateTime end = start.plusDays(7);

        return modelMapper.map(reservationRepository.findActualReservations(start, end), targetListType);
    }

    public ReservationDTO makeReservation(MakeReservationCommand command) {
        Court court = courtRepository.getById(command.getCourtId());
        Customer customer = customerService.getCustomerById(command.getCustomerId());
        LocalDateTime startTime = command.getStartTime();
        LocalDateTime endTime = command.getEndTime();

        if (court.getReservations().stream().anyMatch(r -> r.getStartTime().equals(startTime) || r.getEndTime().equals(endTime))) {
            throw new InvalidReservationException("Court is occupied at this time");
        }

        if (command.getStartTime().toLocalTime().isBefore(court.getOpenHour()) || command.getEndTime().toLocalTime().isAfter(court.getCloseHour())) {
            throw new InvalidReservationException("Court is closed at this time");
        }

        Reservation reservation = new Reservation(startTime,endTime,court,customer);
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public ReservationDTO modifyReservation(ModifyReservationCommand command) {
        Reservation reservation = reservationRepository.getById(command.getReservationId());
        Customer customer = customerService.getCustomerById(command.getCustomerId());
        Court court = courtRepository.getById(command.getCourtId());

        reservation.setCourt(court);
        reservation.setCustomer(customer);
        reservation.setStartTime(command.getStartTime());
        reservation.setEndTime(command.getEndTime());

        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public void deleteReservation(DeleteReservationCommand command) {
        Reservation reservation = reservationRepository.getById(command.getId());

        reservationRepository.delete(reservation);
    }
}
