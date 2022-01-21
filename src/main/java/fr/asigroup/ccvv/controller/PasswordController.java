package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.pojo.PasswordsDTO;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordController {
    private UserService userService;

    public PasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/change-password/")
    public String editPassword(Model model) {
        PasswordsDTO passwords = new PasswordsDTO();
        model.addAttribute("passwords", passwords);
        return "editPassword";
    }

    @PostMapping("/change-password/")
    public String updatePassword(PasswordsDTO passwords, RedirectAttributes ra) throws UserNotFoundException {

        String flash = userService.updatePassword(passwords);

        String flashType;

        if (flash.endsWith("succès")) {
            flashType = "success";
            ra.addFlashAttribute("flash", flash);
            ra.addFlashAttribute("flashType", flashType);
            return "redirect:/";
        } else {
            flashType = "danger";
        }

        ra.addFlashAttribute("flash", flash);
        ra.addFlashAttribute("flashType", flashType);

        return "redirect:/change-password/";
    }
}
