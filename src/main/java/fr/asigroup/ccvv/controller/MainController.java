package fr.asigroup.ccvv.controller;


import fr.asigroup.ccvv.entity.DateCalendrier;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.pojo.MonthCalendarGenerator;
import fr.asigroup.ccvv.repository.RdvRepository;
import fr.asigroup.ccvv.service.UnavailableDaysService;
import fr.asigroup.ccvv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    private UserService userService;
    private RdvRepository rdvRepository;
    private UnavailableDaysService unavailableDaysService;
    public MainController(UserService userService, RdvRepository rdvRepository, UnavailableDaysService unavailableDaysService) {
        this.userService = userService;
        this.rdvRepository = rdvRepository;
        this.unavailableDaysService = unavailableDaysService;
    }

//    @GetMapping
//    public String showSelection(@RequestParam(required = false, defaultValue = "0") Integer offset, Model model) {
//
//        MonthCalendarGenerator monthCalendarGenerator = new MonthCalendarGenerator();
//
//        Map<LocalDate, Map<String, String>> searchedMonthCalendar = monthCalendarGenerator.getMonthCalendar(offset);
//        String searchedMonth = monthCalendarGenerator.getNameOfMonth(offset);
//
//        model.addAttribute("calendar", searchedMonthCalendar);
//        model.addAttribute("month", searchedMonth);
//        model.addAttribute("offset", offset);
//
//        return "index";
//    }

    @GetMapping
    public String index(@RequestParam(required = false, defaultValue = "0") Integer offset, Model model) {
        System.out.println("offset = " + offset);

        MonthCalendarGenerator generator = new MonthCalendarGenerator(rdvRepository, unavailableDaysService);
        Map<LocalDate, DateCalendrier> calendar = generator.getMonthCalendar(offset);

        model.addAttribute("calendar", calendar);
        model.addAttribute("month",generator.getNameOfMonth(offset) + " " + generator.getYear(offset));
        model.addAttribute("offset",offset);

        return "index";
    }

    @GetMapping("/calendrier-detail/{date}")
    public String calendrierDetail(@PathVariable String date, Model model) {
        LocalDate localDate = LocalDate.parse(date);

        List<Rdv> liste = rdvRepository.findAllActiveAndPastByDate(localDate);

        model.addAttribute("liste", liste);
        model.addAttribute("date", localDate);
        return "calendrier-detail";
    }


    @GetMapping("/with-layout/")
    public String showTestWithLayout() {
        return "testWithLayout";
    }

}
