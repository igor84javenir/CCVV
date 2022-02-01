package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.entity.ReasonRdv;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.pojo.RdvComparator;
import fr.asigroup.ccvv.service.*;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;


@Controller
public class RdvController {

    private static final int TIME_FOR_MAIL_CREATION = 10;
    private static final int MAXIMUM_MONTHS_FOR_GET_RDV = 3;
    private static final int MINIMUM_DAYS_FOR_GET_RDV = 1;

    private Rdv newRdv;
    private RdvComparator rdvComparator = new RdvComparator();

    @Autowired
    private RdvService rdvService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ReasonRdvService reasonRdvService;

    @Autowired
    private UserService userService;

    @GetMapping("/rdvs")
    public String showRdvs(Model model) {
        List<Rdv> rdvs = rdvService.getAllByStatus(Rdv.Status.Actif);

        model.addAttribute("rdvComparator", rdvComparator);

        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

    @GetMapping("/rdvs/new")
    public String newRdv(Model model, @RequestParam(required = false) String redirectCheck) throws CityNotFoundException, UserNotFoundException {

        List<City> cities = cityService.getAll();
        List<ReasonRdv> reasonsRdv = reasonRdvService.getAll();

        model.addAttribute("cities",cities);
        model.addAttribute("reasonsRdv",reasonsRdv);

        if ("redirect".equals(redirectCheck)) {
            model.addAttribute("rdv",newRdv);

            // Bad practise, do not use
            Long selectedCityId = newRdv.getCity().getId();
            model.addAttribute("selectedCityId", selectedCityId);
            // END Bad practise, do not use

            boolean hasNoMail = false;
            model.addAttribute("hasNoMail", hasNoMail);

            return"rdvs/newRdv";
        }

        Rdv rdv = new Rdv();
        model.addAttribute("rdv",rdv);

        return"rdvs/newRdv";
    }

    @PostMapping("/rdvs/new/hour")
    public String chooseHour(Rdv rdv, Model model, RedirectAttributes ra, boolean hasNoMail) throws PathNotFoundException, CityNotFoundException {
        newRdv = rdv;
        String flashType;
        String flash;

        if (newRdv.getFirstName().isBlank() || newRdv.getName().isBlank()
                || newRdv.getPhoneNumber().isBlank() || newRdv.getDate() == null
                || ((newRdv.getMail() == null || newRdv.getMail().isBlank()) && !hasNoMail)) {
            flashType = "danger";
            flash = "Tous les champs sont obligatoires";
            ra.addFlashAttribute("flash", flash);
            ra.addFlashAttribute("flashType", flashType);
            String redirectCheck = "redirect";
            ra.addAttribute("redirectCheck", redirectCheck);
            return"redirect:/rdvs/new";
        }

        if (newRdv.getDate().isAfter(LocalDate.now().plusMonths(MAXIMUM_MONTHS_FOR_GET_RDV))
                || newRdv.getDate().isBefore(LocalDate.now().plusDays(MINIMUM_DAYS_FOR_GET_RDV))) {
            flashType = "danger";
            flash = "Les délais minimum / maximum de prise de rendez-vous en ligne sont 1 jour / 3 mois";
            ra.addFlashAttribute("flash", flash);
            ra.addFlashAttribute("flashType", flashType);
            String redirectCheck = "redirect";
            ra.addAttribute("redirectCheck", redirectCheck);
            return"redirect:/rdvs/new";
        }

        rdv.setNoMail(hasNoMail);

        List<AvailableRdvTime> availabilityOfDay = rdvService.getDailySchedule(rdv.getDate(), rdv.getCity(), rdv.getRdvDuration());

        boolean isAvailableTimePresent = false;

        for (AvailableRdvTime availableRdvTime : availabilityOfDay) {
            if (availableRdvTime.isAvailable()) {
                isAvailableTimePresent = true;
                break;
            }
        }

        if (!isAvailableTimePresent) {
            flashType = "danger";
            flash = "Il n'y a pas de créneau disponible pour la date choisie. Choisissez une autre date";
            ra.addFlashAttribute("flash", flash);
            ra.addFlashAttribute("flashType", flashType);
            String redirectCheck = "redirect";
            ra.addAttribute("redirectCheck", redirectCheck);
            return"redirect:/rdvs/new";
        }

        if (hasNoMail) {
            newRdv.setMail("Pas d'adresse mail");
        }

        model.addAttribute("date", rdv.getDate());
        model.addAttribute("city", rdv.getCity().getName());
        model.addAttribute("availabilityOfDay", availabilityOfDay);

        return "rdvs/hour";
    }

    @PostMapping("/rdvs/new/hour/save")
    public String saveRdv(@RequestParam String time)  {
        LocalTime rdvStartTime = LocalTime.parse(time);
        Rdv rdv = newRdv;
        rdv.setTime(rdvStartTime);
        rdvService.save(rdv);
        return "redirect:/rdvs";
    }

    @GetMapping("/rdvs/cancel/{id}")
    public String cancelRdv(@PathVariable Long id) throws RdvNotFoundException {
        rdvService.cancel(id);
        return "redirect:/rdvs";
    }

    @GetMapping("/rdvs/edit/{id}")
    public String editRdv(@PathVariable Long id, Model model) throws RdvNotFoundException, CityNotFoundException, UserNotFoundException {
        Rdv rdv = rdvService.getRdv(id);

        if (rdv.getStatus() != Rdv.Status.Actif) {
            throw new IllegalAccessError();
        }
        List<City> cities = cityService.getAll();
        List<ReasonRdv> reasonsRdv = reasonRdvService.getAll();
        List<User> users = userService.getAll();

        model.addAttribute("rdv", rdv);
        model.addAttribute("cities",cities);
        model.addAttribute("reasonsRdv",reasonsRdv);
        model.addAttribute("users", users);

        return "rdvs/newRdv";
    }

    @GetMapping("/rdvs/passed")
    public String showPassed(Model model) {
        List<Rdv> rdvs = rdvService.getAllByStatus(Rdv.Status.Passé);

        model.addAttribute("rdvComparator", rdvComparator.reversed());

        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

    @GetMapping("/rdvs/cancelled")
    public String showCancelled(Model model) {
        List<Rdv> rdvs = rdvService.getAllByStatus(Rdv.Status.Annulé);

        model.addAttribute("rdvComparator", rdvComparator.reversed());

        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

}

