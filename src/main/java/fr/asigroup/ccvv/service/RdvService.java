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
import java.util.Objects;
import java.util.Optional;


@Service
public class RdvService {
    private static final LocalTime START_OF_WORKING_MORNING = LocalTime.parse("08:00");
    private static final LocalTime END_OF_WORKING_MORNING = LocalTime.parse("12:00");
    private static final LocalTime START_OF_WORKING_AFTERNOON = LocalTime.parse("14:00");
    private static final LocalTime END_OF_WORKING_AFTERNOON = LocalTime.parse("17:00");
    private static final int TIME_RADIUS_FROM_FIRST_RDV_IN_MIN = 30;
    private static final String HOME_CITY_NAME = "Vaison-la-Romaine";


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
        return (List<Rdv>) rdvRepository.findAllActiveAndPastByDate(localDate);
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

    public List<Rdv> getAllByDateAndStatus(LocalDate date, Rdv.Status status) {
        return rdvRepository.findAllByDateAndStatus(date, status);
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

    public List<AvailableRdvTime> getDailySchedule(LocalDate date, City destinationCity, int durationOfNewRdv, Long actualRdvId) throws PathNotFoundException {
        List<Rdv> alreadyExistingRdvOfDay = rdvRepository.findAllByDateAndStatus(date, Rdv.Status.Actif);
        List<AvailableRdvTime> rdvTimes = getAllRdvAvailable();

        if (!alreadyExistingRdvOfDay.isEmpty()) {

            Rdv firstGottenRdv = null;
            Rdv lastRdvOfTheDay = null;
            int rdvCounter = 0;
            for (Rdv existingRdv : alreadyExistingRdvOfDay) {
                if (rdvCounter == 0) {
                    firstGottenRdv = existingRdv;
                    lastRdvOfTheDay = existingRdv;
                } else {
                    if (existingRdv.getCreatedAt().isBefore(firstGottenRdv.getCreatedAt())) {
                        firstGottenRdv = existingRdv;
                    }
                    if (existingRdv.getTime().isAfter(lastRdvOfTheDay.getTime())) {
                        lastRdvOfTheDay = existingRdv;
                    }
                }
                rdvCounter = rdvCounter + 1;
            }

            boolean isInRadiusOfTheFirstGottenRdv = travelDuration(firstGottenRdv.getCity().getName(), destinationCity.getName()) <= TIME_RADIUS_FROM_FIRST_RDV_IN_MIN;

//            System.out.println("isInRadiusOfTheFirstGottenRdv : " + isInRadiusOfTheFirstGottenRdv);

            boolean isOnTheWayBackHome = checkPresenceOnWay(HOME_CITY_NAME, lastRdvOfTheDay.getCity().getName(), destinationCity.getName());

//            System.out.println("isOnTheWayBackHome : " + isOnTheWayBackHome);

            if (!isInRadiusOfTheFirstGottenRdv && !isOnTheWayBackHome) {
                for (AvailableRdvTime availableRdvTime : rdvTimes) {
                    availableRdvTime.setAvailable(false);
                }

                return rdvTimes;
            }

            for (Rdv existingRdv : alreadyExistingRdvOfDay) {

                if (Objects.equals(existingRdv.getId(), actualRdvId)) {
                    continue;
                }
                LocalTime existingRdvStart = existingRdv.getTime();
                LocalTime existingRdvEnd = existingRdvStart.plusMinutes(existingRdv.getRdvDuration());

//                Map<List<String>, Integer> travelFromExistingRdvToNext = pathFinder.findPath(existingRdv.getCity().getName(), destination.getName());
//                Map<List<String>, Integer> travelFromNextRdvToExisting = pathFinder.findPath(destination.getName(), existingRdv.getCity().getName());

//                int travelDurationFromExistingToNext = travelFromExistingRdvToNext
//                        .values()
//                        .stream()
//                        .findFirst()
//                        .orElseThrow(() -> new PathNotFoundException("Path not found from " + existingRdv.getCity().getName() + " to " + destination.getName()));

//                int travelDurationFromNextToExisting = travelFromNextRdvToExisting
//                        .values()
//                        .stream()
//                        .findFirst()
//                        .orElseThrow(() -> new PathNotFoundException("Path not found from " + destination.getName() + " to " + existingRdv.getCity().getName()));

                int travelDurationFromExistingToNext = travelDuration(existingRdv.getCity().getName(), destinationCity.getName());
                int travelDurationFromNextToExisting = travelDuration(destinationCity.getName(), existingRdv.getCity().getName());

                LocalTime possibleStartAfterRdv = existingRdvEnd.plusMinutes(travelDurationFromExistingToNext);
                LocalTime possibleStartBeforeRdv = existingRdvStart.minusMinutes(travelDurationFromNextToExisting).minusMinutes(durationOfNewRdv);

                for (AvailableRdvTime rdvTime : rdvTimes) {
                    rdvTime.setAvailable(checkTimeAvailability(possibleStartAfterRdv, possibleStartBeforeRdv, rdvTime));
                }

            }

        }
        return rdvTimes;
    }

    private boolean checkPresenceOnWay(String homeCityName, String startCityName, String cityForCheck) {
        Map<List<String>, Integer> travel = pathFinder.findPath(startCityName, homeCityName);
        boolean isCityOnTheWay = false;
        for (Map.Entry<List<String>, Integer> entry : travel.entrySet()) {
            for (String cityOnTheWay : entry.getKey()) {
                if (cityOnTheWay.equals(cityForCheck)) {
                    isCityOnTheWay = true;
                    break;
                }
            }
        }

        return isCityOnTheWay;
    }

    private int travelDuration(String firstCityName, String secondCityName) throws PathNotFoundException {
        Map<List<String>, Integer> travel = pathFinder.findPath(firstCityName, secondCityName);
        return travel
                .values()
                .stream()
                .findFirst()
                .orElseThrow(() -> new PathNotFoundException("Path not found from " + firstCityName + " to " + secondCityName));
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

