package fr.asigroup.ccvv.controller;


import fr.asigroup.ccvv.pojo.PasswordsDTO;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class MainController {
    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSelection() {

        return "index";
    }

    @GetMapping("with-layout/")
    public String showTestWithLayout() {
        return "testWithLayout";
    }

    @GetMapping("change-password/{id}")
    public String editPassword(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("id", id);
        PasswordsDTO passwords = new PasswordsDTO();
        model.addAttribute("passwords", passwords);
        return "editPassword";
    }

    @PostMapping("change-password/{id}")
    public String updatePassword(@ModelAttribute("id") long id, Model model, PasswordsDTO passwords, RedirectAttributes ra) throws UserNotFoundException {

        System.out.println("UpdatePassword passwords are : " + passwords);
        System.out.println("UpdatePassword ID is : " + id);

        String flash = userService.updatePassword(id, passwords);

        String flashType;

        if (flash.endsWith("succès")) {
            flashType = "success";
        } else {
            flashType = "danger";
        }

        ra.addFlashAttribute("flash", flash);
        ra.addFlashAttribute("flashType", flashType);

        return "redirect:/";
    }
/*hukfgfkff*/
}
