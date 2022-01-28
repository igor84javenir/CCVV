package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.entity.ReasonRdv;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.pojo.RdvComparator;
import fr.asigroup.ccvv.service.*;
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

import java.time.LocalTime;
import java.util.List;


@Controller
public class RdvController {

    private Rdv newRdv;

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


        RdvComparator rdvComparator = new RdvComparator();
        model.addAttribute("rdvComparator", rdvComparator);

        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

    @GetMapping("/rdvs/new")
    public String newRdv(Model model) throws CityNotFoundException, UserNotFoundException {
        List<City> cities = cityService.getAll();
        List<ReasonRdv> reasonsRdv = reasonRdvService.getAll();
        Rdv rdv = new Rdv();

        model.addAttribute("rdv",rdv);
        model.addAttribute("cities",cities);
        model.addAttribute("reasonsRdv",reasonsRdv);
        return"rdvs/newRdv";
    }

    @PostMapping("/rdvs/new/hour")
    public String chooseHour(Rdv rdv, Model model) throws PathNotFoundException {
        newRdv = rdv;

        List<AvailableRdvTime> availabilityOfDay = rdvService.getDailySchedule(rdv.getDate(), rdv.getCity(), rdv.getReasonRdv().getDurationMinutes());

        model.addAttribute("date", rdv.getDate());
        model.addAttribute("city", rdv.getCity().getName());
        model.addAttribute("availabilityOfDay", availabilityOfDay);

        return "rdvs/hour";
    }

    @PostMapping("/rdvs/new/hour/save")
    public String saveRdv(@RequestParam String time)  {
        LocalTime localTime = LocalTime.parse(time);
        Rdv rdv = newRdv;
        rdv.setTime(localTime);
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
        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

    @GetMapping("/rdvs/cancelled")
    public String showCancelled(Model model) {
        List<Rdv> rdvs = rdvService.getAllByStatus(Rdv.Status.Annulé);
        model.addAttribute("rdvs", rdvs);
        return "rdvs/showRdvs";
    }

}

