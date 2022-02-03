package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.EntityUnavailableDays;
import fr.asigroup.ccvv.service.EntityUnavailableDaysNotFoundException;
import fr.asigroup.ccvv.service.UnavailableDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UnavailableDaysController {
    @Autowired
    private UnavailableDaysService service;

    @GetMapping("/indisponible")
    public String showDaysLis(Model model){
        List<EntityUnavailableDays> listDays = service.listAll();
        model.addAttribute("listDays", listDays);
        return "indisponible";
    }

    @GetMapping("/indisponible/new")
    public String newUnavailable(Model model){
        model.addAttribute("entityUnavailableDays", new EntityUnavailableDays());
        model.addAttribute("pageTitle", "Rajouter des jours non-disponible");
        return "newindisponible";
    }

    @PostMapping("/indisponible/save")
    public String saveUnavailable(EntityUnavailableDays entityUnavailableDays){
        System.out.println(entityUnavailableDays);
        service.save(entityUnavailableDays);
        return "redirect:/indisponible";
    }

    @GetMapping("/indisponible/modifier/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model){
        try {
            EntityUnavailableDays entityUnavailableDays = service.get(id);
            model.addAttribute("entityUnavailableDays", entityUnavailableDays);
            model.addAttribute("pageTitle", "Modifier les jours non-disponible");
            return"newindisponible";
        } catch (EntityUnavailableDaysNotFoundException e) {
            e.printStackTrace();
            return "redirect:/indisponible";
        }

    }

    @GetMapping("/indisponible/supprimer/{id}")
    public String showdelete(@PathVariable("id") Long id){
        try {
            service.cancel(id);
        } catch (EntityUnavailableDaysNotFoundException e) {
            e.printStackTrace();

        }
        return "redirect:/indisponible";
    }
}
