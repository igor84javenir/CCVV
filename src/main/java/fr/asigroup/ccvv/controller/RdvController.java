package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.entity.ReasonRdv;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class RdvController {

    @Autowired
    private RdvService rdvservice;
    @Autowired
    private CityService cityService;
    @Autowired
    private ReasonRdvService reasonRdvService;
    @Autowired
    private UserService userService;

    @GetMapping("/rdvs")
    public String showRdvs(Model model) {
        List<Rdv> rdvs = rdvservice.getAllExist(true);
        System.out.println(rdvs);
        model.addAttribute("rdvs", rdvs);
        return "rdvs";
    }

    @GetMapping("/rdvs/nouveau")
    public String showNouveaRdv(Model model) throws CityNotFoundException, UserNotFoundException {
        List<City> cities = cityService.getAll();
        List<ReasonRdv> reasonsRdv = reasonRdvService.getAll();
        List<User> users = userService.getAll();
        Rdv rdv = new Rdv();

        model.addAttribute("rdv",rdv);
        model.addAttribute("cities",cities);
        model.addAttribute("reasonsRdv",reasonsRdv);
        model.addAttribute("users", users);
        return"nouveaurdv";
    }

    @PostMapping("/rdvs/enregistrer")
    public String enregistrer(Model model, Rdv rdv)  {
        System.out.println("on est dans enregistrer post rdv");
        rdvservice.enregistrer(rdv);
        return "redirect:/rdvs";
    }

    @GetMapping("/rdvs/supprimer/{id}")
    public String supprimerRdv(@PathVariable Long id) throws RdvNotFoundException {
        System.out.println("on est dans supprimer rdv");
        rdvservice.supprimer(id);
        return "redirect:/rdvs";
    }

    @GetMapping("/rdvs/modifier/{id}")
    public String modifierRdv(@PathVariable Long id, Model model) throws RdvNotFoundException, CityNotFoundException, UserNotFoundException {
        List<City> cities = cityService.getAll();
        List<ReasonRdv> reasonsRdv = reasonRdvService.getAll();
        List<User> users = userService.getAll();
        Rdv rdv = rdvservice.getRdv(id);
        model.addAttribute("rdv", rdv);
        model.addAttribute("cities",cities);
        model.addAttribute("reasonsRdv",reasonsRdv);
        model.addAttribute("users", users);

        return "nouveaurdv";
    }

}

