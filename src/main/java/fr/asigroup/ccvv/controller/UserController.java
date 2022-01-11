package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.service.CityNotFoundException;
import fr.asigroup.ccvv.service.CityService;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/main/admin/users")
public class UserController {
    private static final int ROWS_PER_PAGE = 2;

    private UserService userService;
    private CityService cityService;

    public UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }



    @GetMapping(value = { "/","" })
    public String showUsers(@RequestParam(name = "page-number", defaultValue = "1") Integer pageNumber, Model model) throws UserNotFoundException, CityNotFoundException {

//        System.out.println(pageNumber);

        Page<User> pagedUsers = userService.getAllPagedExist(pageNumber - 1, ROWS_PER_PAGE);
        int lastPage = pagedUsers.getTotalPages() + 1;
        int currentPage = pageNumber;

        List<Integer> pages = IntStream
                .range(1, lastPage)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("users", pagedUsers);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", currentPage);


//        List<User> users = userService.getAll();
//        model.addAttribute("users", users);

//        User newUser = new User();
//        model.addAttribute("user", newUser);
//        List<City> cities = cityService.getAll();
//        model.addAttribute("cities", cities);





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
    public String showDeactivatedUsers(@RequestParam(name = "page-number", defaultValue = "1") Integer pageNumber, Model model) throws UserNotFoundException, CityNotFoundException {
//        List<User> users = userService.getAllDeactivated();
//        model.addAttribute("users", users);

        Page<User> pagedUsers = userService.getAllPagedNotExist(pageNumber - 1, ROWS_PER_PAGE);
        int lastPage = pagedUsers.getTotalPages() + 1;
        int currentPage = pageNumber;

        List<Integer> pages = IntStream
                .range(1, lastPage)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("users", pagedUsers);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", currentPage);

//        List<City> cities = cityService.getAll();
//        User newUser = new User();
//        model.addAttribute("user", newUser);
//        model.addAttribute("cities", cities);

        return "users/show";
    }
}
