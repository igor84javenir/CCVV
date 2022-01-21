package fr.asigroup.ccvv.controller;


import fr.asigroup.ccvv.pojo.PasswordsDTO;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/with-layout/")
    public String showTestWithLayout() {
        return "testWithLayout";
    }

}
