package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.pojo.PathFinder;
import fr.asigroup.ccvv.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RdvService {

    private RdvRepository rdvRepository;
    private PathFinder pathFinder;

    public RdvService(RdvRepository rdvRepository, PathFinder pathFinder) {
        this.rdvRepository = rdvRepository;
        this.pathFinder = pathFinder;
    }

    public List<Rdv> listRdvs() {
        return (List<Rdv>) rdvRepository.findAll();
    }

    public List<Rdv> listRdvsByDate(LocalDate localDate) {
        return (List<Rdv>) rdvRepository.findAllByDateAndTime(localDate);
    }
    public void save(Rdv rdv) {

        rdv.setStatus(Rdv.Status.Active);
        rdv.setCreatedAt(LocalDateTime.now());
        rdv.setCreatedBy("System Create");
        rdv.setModifiedBy("System Edit");
        rdv.setModifiedAt(LocalDateTime.now());

        rdvRepository.save(rdv);
    }

    public void supprimer(Long id) throws RdvNotFoundException {
        Long count = rdvRepository.countById(id);
        if (count == null || count == 0) {
            throw new RdvNotFoundException("le RDV " + id + "exists pas");
        }
        /*rdvrepo.deleteById(id);*/
        Optional<Rdv> optionalRdv = rdvRepository.findById(id);

        if (optionalRdv.isPresent()) {
            Rdv rdv = optionalRdv.get();
            rdv.setStatus(Rdv.Status.Cancelled);
            rdvRepository.save(rdv);
        }


    }

    public List<Rdv> getAllByStatus(Rdv.Status status){
        return rdvRepository.findAllByStatus(status);
    }

    public Rdv getRdv(Long id) throws RdvNotFoundException {
        Optional<Rdv> rdv = rdvRepository.findById(id);
        if(rdv.isPresent())
            return rdv.get();
        throw new RdvNotFoundException("l'utilisateur" + id + "n'existe pas");
    }

    public List<Rdv> getAll() {
        return rdvRepository.findAll();
    }

    public List<AvailableRdvTime> getDailySchedule(LocalDate date, City destination) throws PathNotFoundException {
        List<Rdv> alreadyExistingRdvOfDay = rdvRepository.findAllByDateAndStatus(date, Rdv.Status.Active);
        List<AvailableRdvTime> availableRdvTimes = getAllRdvAvailable();

        if (!alreadyExistingRdvOfDay.isEmpty()) {
            for (Rdv rdv : alreadyExistingRdvOfDay) {
                LocalTime rdvStart = rdv.getTime();
                LocalTime rdvEnd = rdvStart.plusMinutes(rdv.getReasonRdv().getDurationMinutes());
                PathFinder pathFinder = new PathFinder();
                Map<List<String>, Integer> travel = pathFinder.findPath(rdv.getCity().getName(), destination.getName());

                int travelDuration = travel
                        .values()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new PathNotFoundException("Path not found between " + rdv.getCity().getName() + " and " + destination.getName()));

                LocalTime possibleStartOfNextRdv = rdvEnd.plusMinutes(travelDuration);

                for (AvailableRdvTime availableRdvTime : availableRdvTimes) {
                    if ((availableRdvTime.getTime().isAfter(rdvStart) && availableRdvTime.getTime().isBefore(possibleStartOfNextRdv))
                            || availableRdvTime.getTime().equals(rdvStart)) {
                        availableRdvTime.setAvailable(false);
                    }
                }

            }

        }
        return availableRdvTimes;
    }

    private List<AvailableRdvTime> getAllRdvAvailable() {
        List<AvailableRdvTime> availableRdvTimes = new LinkedList<>();

        LocalTime morningStart = LocalTime.parse("08:00");
        LocalTime morningEnd = LocalTime.parse("12:00");
        LocalTime afternoonStart = LocalTime.parse("14:00");
        LocalTime afternoonEnd = LocalTime.parse("17:00");
        LocalTime workTime;

        List<LocalTime> schedule = new LinkedList<>();

        workTime = morningStart;

        while (workTime.isBefore(morningEnd.minusMinutes(30))) {
            if (workTime.equals(morningStart)) {
                availableRdvTimes.add(new AvailableRdvTime(workTime, true));
            }
            workTime = workTime.plusMinutes(15);
            availableRdvTimes.add(new AvailableRdvTime(workTime, true));
        }

        workTime = afternoonStart;

        while (workTime.isBefore(afternoonEnd.minusMinutes(30))) {
            if (workTime.equals(afternoonStart)) {
                availableRdvTimes.add(new AvailableRdvTime(workTime, true));
            }
            workTime = workTime.plusMinutes(15);
            availableRdvTimes.add(new AvailableRdvTime(workTime, true));
        }

        return availableRdvTimes;

    }
}

