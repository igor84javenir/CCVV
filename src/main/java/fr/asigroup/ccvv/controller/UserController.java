package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.service.CityNotFoundException;
import fr.asigroup.ccvv.service.CityService;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main/admin/users/")
public class UserController {
    private UserService userService;
    private CityService cityService;

    public UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String showUsers(Model model) throws UserNotFoundException, CityNotFoundException {
        List<User> users = userService.getAll();

        List<City> cities = cityService.getAll();

        User newUser = new User();
        model.addAttribute("user", newUser);

        model.addAttribute("cities", cities);
        model.addAttribute("users", users);


        return "users/show";
    }

    @GetMapping("/new/")
    public String newUser(Model model) throws CityNotFoundException {

        List<City> cities = cityService.getAll();

        User newUser = new User();
        model.addAttribute("user", newUser);
        model.addAttribute("cities", cities);

        return "users/new";

    }

    @PostMapping("/new/save")
    public String saveNewUser(User user) {

        userService.save(user);

        return "redirect:..";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        List<City> cities = null;
        try {
            cities = cityService.getAll();
        } catch (CityNotFoundException e) {
            e.printStackTrace();
        }

        model.addAttribute("cities", cities);
        model.addAttribute("user", user);

        return "users/new";
    }

    @PostMapping("/edit/save")
    public String updateUser(User user) {

        userService.update(user);

        return "redirect:..";
    }

    @GetMapping("/delete/{id}")
    public String deactivateUser(@PathVariable("id") long id) throws UserNotFoundException {
        User user = userService.getUserById(id);

        user.setExist(false);
        user.setModifiedBy("Remover Name");
        userService.update(user);

        return "redirect:..";
    }

    @GetMapping("/deleted-users")
    public String showDeactivatedUsers(Model model) throws UserNotFoundException, CityNotFoundException {
        List<User> users = userService.getAllDeactivated();

        List<City> cities = cityService.getAll();

        User newUser = new User();
        model.addAttribute("user", newUser);

        model.addAttribute("cities", cities);
        model.addAttribute("users", users);


        return "users/show";
    }
}
