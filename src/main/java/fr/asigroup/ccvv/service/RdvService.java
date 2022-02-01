package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.pojo.PathFinder;
import fr.asigroup.ccvv.repository.RdvRepository;
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
    private static final LocalTime START_OF_WORKING_MORNING = LocalTime.parse("08:00");
    private static final LocalTime END_OF_WORKING_MORNING = LocalTime.parse("12:00");
    private static final LocalTime START_OF_WORKING_AFTERNOON = LocalTime.parse("14:00");
    private static final LocalTime END_OF_WORKING_AFTERNOON = LocalTime.parse("17:00");


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

        rdv.setStatus(Rdv.Status.Actif);
        rdv.setCreatedAt(LocalDateTime.now());
        rdv.setCreatedBy("System Create");
        rdv.setModifiedBy("System Edit");
        rdv.setModifiedAt(LocalDateTime.now());

        rdvRepository.save(rdv);
    }

    public void cancel(Long id) throws RdvNotFoundException {
        Long count = rdvRepository.countById(id);
        if (count == null || count == 0) {
            throw new RdvNotFoundException("le RDV " + id + "exists pas");
        }
        Optional<Rdv> optionalRdv = rdvRepository.findById(id);

        if (optionalRdv.isPresent()) {
            Rdv rdv = optionalRdv.get();
            rdv.setStatus(Rdv.Status.Annulé);
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

    public List<AvailableRdvTime> getDailySchedule(LocalDate date, City destination, int durationOfNewRdv) throws PathNotFoundException {
        List<Rdv> alreadyExistingRdvOfDay = rdvRepository.findAllByDateAndStatus(date, Rdv.Status.Actif);
        List<AvailableRdvTime> rdvTimes = getAllRdvAvailable();

        if (!alreadyExistingRdvOfDay.isEmpty()) {
            for (Rdv existingRdv : alreadyExistingRdvOfDay) {
                LocalTime existingRdvStart = existingRdv.getTime();
                LocalTime existingRdvEnd = existingRdvStart.plusMinutes(existingRdv.getRdvDuration());

                System.out.println("RDV END TIME : " + existingRdvEnd.toString());

                Map<List<String>, Integer> travelFromExistingRdvToNext = pathFinder.findPath(existingRdv.getCity().getName(), destination.getName());
                Map<List<String>, Integer> travelFromNextRdvToExisting = pathFinder.findPath(destination.getName(), existingRdv.getCity().getName());

                int travelDurationFromExistingToNext = travelFromExistingRdvToNext
                        .values()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new PathNotFoundException("Path not found from " + existingRdv.getCity().getName() + " to " + destination.getName()));

                int travelDurationFromNextToExisting = travelFromNextRdvToExisting
                        .values()
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new PathNotFoundException("Path not found from " + destination.getName() + " to " + existingRdv.getCity().getName()));

                LocalTime possibleStartAfterRdv = existingRdvEnd.plusMinutes(travelDurationFromExistingToNext);
                LocalTime possibleStartBeforeRdv = existingRdvStart.minusMinutes(travelDurationFromNextToExisting).minusMinutes(durationOfNewRdv);

                for (AvailableRdvTime rdvTime : rdvTimes) {
                    rdvTime.setAvailable(checkTimeAvailability(possibleStartAfterRdv, possibleStartBeforeRdv, rdvTime));
                }

            }

        }
        return rdvTimes;
    }

    private boolean checkTimeAvailability(LocalTime possibleStartAfterRdv,
                                          LocalTime possibleStartBeforeRdv,
                                          AvailableRdvTime rdvTime) {

        if (rdvTime.getTime().isBefore(possibleStartAfterRdv) && rdvTime.getTime().isAfter(possibleStartBeforeRdv)) {
            return false;
        }

        return rdvTime.isAvailable();

    }

    private List<AvailableRdvTime> getAllRdvAvailable() {
        List<AvailableRdvTime> availableRdvTimes = new LinkedList<>();

        LocalTime workTime;

        List<LocalTime> schedule = new LinkedList<>();

        workTime = START_OF_WORKING_MORNING;

        while (workTime.isBefore(END_OF_WORKING_MORNING.minusMinutes(30))) {
            if (workTime.equals(START_OF_WORKING_MORNING)) {
                availableRdvTimes.add(new AvailableRdvTime(workTime, true));
            }
            workTime = workTime.plusMinutes(15);
            availableRdvTimes.add(new AvailableRdvTime(workTime, true));
        }

        workTime = START_OF_WORKING_AFTERNOON;

        while (workTime.isBefore(END_OF_WORKING_AFTERNOON.minusMinutes(30))) {
            if (workTime.equals(START_OF_WORKING_AFTERNOON)) {
                availableRdvTimes.add(new AvailableRdvTime(workTime, true));
            }
            workTime = workTime.plusMinutes(15);
            availableRdvTimes.add(new AvailableRdvTime(workTime, true));
        }

        return availableRdvTimes;

    }
}

