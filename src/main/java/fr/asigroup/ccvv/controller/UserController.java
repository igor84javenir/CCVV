package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.User;
import fr.asigroup.ccvv.security.UserDetailsImpl;
import fr.asigroup.ccvv.service.CityNotFoundException;
import fr.asigroup.ccvv.service.CityService;
import fr.asigroup.ccvv.service.UserNotFoundException;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/main/admin/users")
public class UserController {
    private static final int ROWS_PER_PAGE = 10;

    private UserService userService;
    private CityService cityService;

    public UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }
    
    

    @GetMapping(value = { "/","" })
    public String showUsers(@RequestParam(name = "page-number", defaultValue = "1") Integer pageNumber, Model model) throws UserNotFoundException, CityNotFoundException {

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
    public String saveUser(User user, RedirectAttributes ra, Model model) throws CityNotFoundException {
        String flash = userService.save(user);
        String flashType;

        if (flash.endsWith("Utilisez un autre nom") || flash.endsWith("de 8 à 20 caractères") || flash.endsWith("champs sont obligatoires")) {
            flashType = "danger";

            model.addAttribute("flash", flash);
            model.addAttribute("flashType", flashType);

            List<City> cities = cityService.getAll();

            model.addAttribute("cities", cities);
            model.addAttribute("user", user);

            return "users/new";
        }

        flashType = "success";
        ra.addFlashAttribute("flash", flash);
        ra.addFlashAttribute("flashType", flashType);

        return "redirect:..";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) throws UserNotFoundException, CityNotFoundException {
        User user = userService.getUserById(id);

        if ((user.getUserRole().equals(User.UserRole.ROLE_ADMIN) || user.getUserRole().equals(User.UserRole.ROLE_SUPERADMIN))) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!auth.getAuthorities().toString().contains(User.UserRole.ROLE_SUPERADMIN.toString())) {
                throw new AccessDeniedException("403 returned");
            }
        }

        List<City> cities = cityService.getAll();

        model.addAttribute("cities", cities);
        model.addAttribute("user", user);


        return "users/new";
    }

    @PostMapping("/edit/save")
    public String updateUser(User user, Model model, RedirectAttributes ra) throws CityNotFoundException {

        String flash = userService.update(user);
        String flashType;

        if (flash.endsWith("Utilisez un autre nom") || flash.endsWith("champs sont obligatoires")) {
            flashType = "danger";
            model.addAttribute("flash", flash);
            model.addAttribute("flashType", flashType);

            List<City> cities = cityService.getAll();

            model.addAttribute("cities", cities);
            model.addAttribute("user", user);

            return "users/new";
        }

        flashType = "success";
        ra.addFlashAttribute("flash", flash);
        ra.addFlashAttribute("flashType", flashType);

        return "redirect:..";
    }

    @GetMapping("/delete/{id}")
    public String deactivateUser(@PathVariable("id") long id) throws UserNotFoundException {
        User user = userService.getUserById(id);

        user.setExist(false);
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
