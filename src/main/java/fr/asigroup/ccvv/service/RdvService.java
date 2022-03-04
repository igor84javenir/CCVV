package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.EntityUnavailableDays;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.pojo.PathFinder;
import fr.asigroup.ccvv.repository.RdvRepository;
import fr.asigroup.ccvv.repository.UserRepository;
import fr.asigroup.ccvv.security.CurrentUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class RdvService {
    private static final LocalTime START_OF_WORKING_MORNING = LocalTime.parse("09:00");
    private static final LocalTime END_OF_WORKING_MORNING = LocalTime.parse("12:00");
    private static final LocalTime START_OF_WORKING_AFTERNOON = LocalTime.parse("13:30");
    private static final LocalTime END_OF_WORKING_AFTERNOON = LocalTime.parse("17:30");
    private static final int TIME_RADIUS_FROM_FIRST_RDV_IN_MIN = 30;
    private static final String HOME_CITY_NAME = "Vaison-la-Romaine";


    private RdvRepository rdvRepository;
    private PathFinder pathFinder;
    private UserRepository userRepository;
    private UserService userService;
    private MailService mailService;

    public RdvService(RdvRepository rdvRepository, PathFinder pathFinder,UserRepository userRepository,UserService userService,MailService mailService) {
        this.rdvRepository = rdvRepository;
        this.pathFinder = pathFinder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    public List<Rdv> listRdvs() {
        return (List<Rdv>) rdvRepository.findAll();
    }

    public List<Rdv> listRdvsByDate(LocalDate localDate) {
        return (List<Rdv>) rdvRepository.findAllActiveAndPastByDate(localDate);
    }
    public void save(Rdv rdv) {

//        System.out.println("rdv.getCreatedBy()" + rdv.getCreatedBy());
        boolean isNew = (rdv.getId() == 0);

        rdv.setStatus(Rdv.Status.Actif);

//        rdv.setCreatedBy("System Create");
//        rdv.setModifiedBy("System Edit");
//
//        System.out.println("rdv.getCreatedBy()" + rdv.getCreatedBy());
        if (rdv.getCreatedBy() == null || rdv.getCreatedBy().isBlank()) {
            rdv.setCreatedBy(CurrentUser.getCurrentUserDetails().getUsername());
            rdv.setCreatedAt(LocalDateTime.now());
        }

        rdv.setModifiedBy(CurrentUser.getCurrentUserDetails().getUsername());
        rdv.setModifiedAt(LocalDateTime.now());


        rdvRepository.save(rdv);

        List<User> mailRecipients = new ArrayList<>();
        List<User> utilisateurs = new ArrayList<>();
        try {
            utilisateurs = userService.getAll();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        for (User user : utilisateurs) {

            if (user.getUserRole() == User.UserRole.ROLE_ADMIN) {
                mailRecipients.add(user);
            } else {
                if (user.getUserRole() == User.UserRole.ROLE_UTILISATEUR && user.getCity() == rdv.getCity()) {
                    mailRecipients.add(user);
                }
            }
        }
        for (User user : mailRecipients) {
            String receivers = user.getMail();

            if (isNew) {

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            mailService.envoiEmail(receivers, "Objet : Confirmation de réservation France Services le : " + rdv.getDate().format(dateFormat) + " à  " + rdv.getCity().getName(),
                    "Madame, Monsieur,\n" +
                            "\n" +
                            "Dans le cadre de l'itinérance France Services, un RDV a été pris à " + rdv.getCity().getName()+ " le " + rdv.getDate().format(dateFormat) + " à " + rdv.getTime() + ".\n" +
                            "Vous en trouverez le récapitulatif ci-dessous.\n" +
                            "\n" +
                            "Nom : " +  rdv.getFirstName() + " " + rdv.getName() + "\n" +
                            "Date : " + rdv.getDate().format(dateFormat)+ "\n" +
                            "Heure : " + rdv.getTime() + "\n" +
                            "Lieu : " + rdv.getCity().getName() + "\n" +
                            "Motif : " + rdv.getReasonRdv().getName() + "\n" +
                            "\n" +
                            "Vous trouverez une liste indicative des documents nécessaires à la bonne réalisation de la démarche sur la page suivante : {lien vers le PDF}.\n" +
                            "\n" +
                            "En tant que mairie d'accueil, vous avez la possbilité de modifier ou d'annuler le présent rendez-vous directement sur la page web du service de prise de rendez-vous.\n" +
                            "\n" +
                            "Bien cordialement,\n" +
                            "\n" +
                            "L'équipe de l'Espace France Services Vaison Ventoux");

                String ownreceivers = rdv.getMail();
                if (ownreceivers != null && ownreceivers != "Pas d'adresse mail") {
                    mailService.envoiEmail(ownreceivers, "Objet : Confirmation RDV France Services du " + rdv.getDate().format(dateFormat) + " à  " + rdv.getCity().getName(),
                            "Madame, Monsieur,\n" +
                                    "\n" +
                                    "L'équipe de l'Espace France Services Vaison Ventoux confirme la prise en compte de votre rendez-vous dont vous trouverez le récapitulatif ci-dessous :\n" +
                                    "\n" +
                                    "Nom : " +  rdv.getFirstName() + " " + rdv.getName() + "\n" +
                                    "Date : " + rdv.getDate().format(dateFormat)+ "\n" +
                                    "Heure : " + rdv.getTime() + "\n" +
                                    "Lieu : " + rdv.getCity().getName() + "\n" +
                                    "Motif : " + rdv.getReasonRdv().getName() + "\n" +
                                    "\n" +
                                    "Pensez à vous munir de tous les documents nécessaires à la réalisation de votre démarche. Vous en trouverez une liste indicative sur la page suivante : {lien vers le PDF}.\n" +
                                    "\n" +
                                    "Merci de vous présenter à l'heure prévue.\n" +
                                    "\n" +
                                    "Ceci est un email automatique. Pour toute demande de modification ou d'annulation, veuillez contacter directement votre mairie ou nous contacter par email : vaison-ventoux@france-services.gouv.fr ou par téléphone au 04 90 36 52 13.\n" +
                                    "\n" +
                                    "Bien cordialement,\n" +
                                    "\n" +
                                    "L'équipe de l'Espace France Services Vaison Ventoux");

                }

        } else
              {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                mailService.envoiEmail(receivers, "Objet : Modification du RDV France Services à " +  rdv.getCity().getName(),
                        "Madame, Monsieur,\n" +
                                "\n" +
                                "L'équipe de l'Espace France Services Vaison Ventoux vous informe de la modification du rendez-vous initialement pris à {commune} le {ancienne date}. Veuillez trouver ci-dessous les détails du nouveau rendez-vous :\n" +
                                "\n" +
                                "Nom : " +  rdv.getFirstName() + " " + rdv.getName() + "\n" +
                                "Date : " + rdv.getDate().format(dateFormat)+ "\n" +
                                "Heure : " + rdv.getTime() + "\n" +
                                "Lieu : " + rdv.getCity().getName() + "\n" +
                                "Motif : " + rdv.getReasonRdv().getName() + "\n" +
                                "\n" +
                                "Vous trouverez une liste indicative des documents nécessaires à la bonne réalisation de la démarche sur la page suivante : {lien vers le PDF}.\n" +
                                "\n" +
                                "En tant que mairie d'accueil, vous avez la possbilité de modifier ou d'annuler le présent rendez-vous directement sur la page web du service de prise de rendez-vous.\n" +
                                "\n" +
                                "Bien cordialement,\n" +
                                "\n" +
                                "L'équipe de l'Espace France Services Vaison Ventoux");


                  String ownreceivers = rdv.getMail();
                  if (ownreceivers != null && ownreceivers != "Pas d'adresse mail") {
                      mailService.envoiEmail(ownreceivers, "Objet : Modification du RDV France Services à  " + rdv.getCity().getName(),
                              "Madame, Monsieur,\n" +
                                      "\n" +
                                      "L'équipe de l'Espace France Services Vaison Ventoux vous informe de la modification de votre rendez-vous initialement pris à {commune} le {date ancien RDV}. Veuillez trouver ci-dessous les détails de votre nouveau rendez-vous :\n" +
                                      "\n" +
                                      "Nom : " +  rdv.getFirstName() + " " + rdv.getName() + "\n" +
                                      "Date : " + rdv.getDate().format(dateFormat)+ "\n" +
                                      "Heure : " + rdv.getTime() + "\n" +
                                      "Lieu : " + rdv.getCity().getName() + "\n" +
                                      "Motif : " + rdv.getReasonRdv().getName() + "\n" +
                                      "\n" +
                                      "Pensez à vous munir de tous les documents nécessaires à la réalisation de votre démarche. Vous en trouverez une liste indicative sur la page suivante : {lien vers le PDF}.\n" +
                                      "\n" +
                                      "Merci de vous présenter à l'heure prévue.\n" +
                                      "\n" +
                                      "Ceci est un email automatique. Pour toute demande de modification ou d'annulation, veuillez contacter directement votre mairie ou nous contacter par email : vaison-ventoux@france-services.gouv.fr ou par téléphone au 04 90 36 52 13.\n" +
                                      "\n" +
                                      "Bien cordialement,\n" +
                                      "\n" +
                                      "L'équipe de l'Espace France Services Vaison Ventoux");

                  }

            }

        }

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


            List<User> mailRecipients = new ArrayList<>();
            List<User> utilisateurs = new ArrayList<>();
            try {
                utilisateurs = userService.getAll();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            for (User user : utilisateurs) {

                if (user.getUserRole() == User.UserRole.ROLE_ADMIN) {
                    mailRecipients.add(user);
                } else {
                    if (user.getUserRole() == User.UserRole.ROLE_UTILISATEUR && user.getCity() == rdv.getCity()) {
                        mailRecipients.add(user);
                    }
                }
            }
            for (User user : mailRecipients) {
                String receivers = user.getMail();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                mailService.envoiEmail(receivers, "Objet : Annulation du RDV France Services à " + rdv.getCity().getName() + " le " + rdv.getDate().format(dateFormat),
                        "Madame, Monsieur,\n" +
                                "\n" +
                                "L'équipe de l'Espace France Services Vaison Ventoux vous informe de l'annulation du rendez-vous initialement pris à " + rdv.getCity().getName() + " le " + rdv.getDate().format(dateFormat)+ " .\n" +
                                "En tant que mairie d'accueil, vous avez la possbilité de reprogrammer un rendez-vous directement sur la page web du service de prise de rendez-vous.\n" +
                                "\n" +
                                "Bien cordialement,\n" +
                                "\n" +
                                "L'équipe de l'Espace France Services Vaison Ventoux");
            }



        String ownreceivers = rdv.getMail();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if (ownreceivers != null && !(ownreceivers.equals("Pas d'adresse mail") )) {
            mailService.envoiEmail(ownreceivers, "Objet : Annulation du RDV France Services à " + rdv.getCity().getName(),
                    "Madame, Monsieur,\n" +
                            "\n" +
                            "L'équipe de l'Espace France Services Vaison Ventoux vous informe de l'annulation de votre rendez-vous initialement pris à " + rdv.getCity().getName() + " le " + rdv.getDate().format(dateFormat) + " .\n" +
                            "\n" +
                            "Ceci est un email automatique. Pour toute demande d'information supplémentaire, veuillez contacter directement votre mairie ou nous contacter par email : vaison-ventoux@france-services.gouv.fr ou par téléphone au 04 90 36 52 13.\n" +
                            "\n" +
                            "Bien cordialement,\n" +
                            "\n" +
                            "L'équipe de l'Espace France Services Vaison Ventoux");

        }
    }

    }



    public List<Rdv> getAllByStatus(Rdv.Status status){
        return rdvRepository.findAllByStatus(status);
    }

    public List<Rdv> getAllByDateAndStatus(LocalDate date, Rdv.Status status) {
        return rdvRepository.findAllByDateAndStatus(date, status);
    }

    public Rdv getRdvById(Long id) throws RdvNotFoundException {
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

    public void doRGPD() {

        String rgpd = "RGPD";

        List<Rdv> rdvs = rdvRepository.findAll();

        for (Rdv rdv : rdvs) {

            boolean isChangesRequired = false;

            if (rdv.getDate().plusDays(30).isAfter(LocalDate.now())) {
                continue;
            }

            if (!rdv.getName().equals(rgpd)) {
                isChangesRequired = true;
                rdv.setName(rgpd);
            }

            if (!rdv.getFirstName().equals(rgpd)) {
                isChangesRequired = true;
                rdv.setFirstName(rgpd);
            }

            if (!rdv.getPhoneNumber().equals(rgpd)) {
                isChangesRequired = true;
                rdv.setPhoneNumber(rgpd);
            }

            if (!rdv.getMail().equals(rgpd)) {
                isChangesRequired = true;
                rdv.setNoMail(false);
                rdv.setMail(rgpd);
            }

            if (isChangesRequired) {
                rdvRepository.save(rdv);
            }
        }
    }

    public void doPastRdv() {

        Rdv.Status past = Rdv.Status.Passé;

        List<Rdv> rdvs = rdvRepository.findAllByStatus(Rdv.Status.Actif);

        for (Rdv rdv : rdvs) {

            if (rdv.getDate().plusDays(1).isAfter(LocalDate.now())) {
                continue;
            }

            rdv.setStatus(past);

            rdvRepository.save(rdv);
        }
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

