package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.service.ReasonRdvNotFoundException;
import fr.asigroup.ccvv.service.ReasonRdvService;
import fr.asigroup.ccvv.entity.ReasonRdv;

import fr.asigroup.ccvv.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReasonRdvController {

    @Autowired
    ReasonRdvService service;


    @GetMapping("/reasons_rdv")
    public String liste(Model m) {
        List<ReasonRdv> liste = service.getAll();
        m.addAttribute("reasonsList", liste);

        return "rdvReasons/reasonsShow";
    }


    @GetMapping("/reasons_rdv/ajout")
    public String add(Model m) {
        ReasonRdv reason = new ReasonRdv();
        m.addAttribute("reason", reason);

        return "rdvReasons/reasonNewOrEdit";
    }

    @PostMapping("/reasons_rdv/enregistrer")
    public String save(ReasonRdv reason) {
        reason.setCreatedAt(LocalDateTime.now());
        reason.setModifiedAt(LocalDateTime.now());
        reason.setCreatedBy("admin");
        reason.setModifiedBy("admin");
        service.save(reason);

        return "redirect:/reasons_rdv";
    }

    @GetMapping("/reasons_rdv/supprimer/{id}")
    public String delete(@PathVariable Long id) throws ReasonRdvNotFoundException {
        service.delete(id);

        return "redirect:/reasons_rdv";
    }
    @GetMapping("/reasons_rdv/deactivate/{id}")
    public String deactivateReasons_rdv(@PathVariable("id") long id) throws UserNotFoundException {
        ReasonRdv reasonRdv = service.getReasonRdv(id);

        reasonRdv.setExist(false);
        reasonRdv.setModifiedBy("Remover Name");
        service.update(reasonRdv);

        return "redirect:/reasons_rdv";

    }
    @GetMapping("/reasons_rdv/edit/{id}")
    public String editReasons_rdv(@PathVariable("id") long id,Model model) throws UserNotFoundException {
        ReasonRdv reasonRdv = service.getReasonRdv(id);


     model.addAttribute("reason",reasonRdv);
        return "rdvReasons/reasonNewOrEdit";
    }

    }
