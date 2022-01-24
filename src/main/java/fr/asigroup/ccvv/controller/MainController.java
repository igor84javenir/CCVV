package fr.asigroup.ccvv.controller;


import fr.asigroup.ccvv.pojo.MonthCalendarGenerator;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;


@Controller
public class MainController {
    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSelection(@RequestParam(required = false, defaultValue = "0") Integer offset, Model model) {

        MonthCalendarGenerator monthCalendarGenerator = new MonthCalendarGenerator();

        Map<LocalDate, Map<String, String>> searchedMonthCalendar = monthCalendarGenerator.getMonthCalendar(offset);
        String searchedMonth = monthCalendarGenerator.getNameOfMonth(offset);

        model.addAttribute("calendar", searchedMonthCalendar);
        model.addAttribute("month", searchedMonth);
        model.addAttribute("offset", offset);

        return "index";
    }

    @GetMapping("/with-layout/")
    public String showTestWithLayout() {
        return "testWithLayout";
    }

}
