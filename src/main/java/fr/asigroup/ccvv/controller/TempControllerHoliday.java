package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.pojo.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/holiday/")
public class TempControllerHoliday {

    @GetMapping("/")
    public String findPath(Model model, @ModelAttribute("returnedCheckDate") String returnedCheckDate) {

        if (returnedCheckDate != null && !returnedCheckDate.isEmpty()) {
            Holiday holiday = new Holiday();
            String isHoliday = new Holiday().isHoliday(LocalDate.parse(returnedCheckDate));
            String result;

            if (!isHoliday.equals("No")) {
                result = returnedCheckDate + " est " + isHoliday;
            } else {
                result = returnedCheckDate + " n'est pas un jour férié";
            }


            model.addAttribute("resultOfCheck", result);
        }

        return "tempHolidayController";
    }

    @PostMapping("/check/")
    public String showPath(@RequestParam String myCheckDate, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("returnedCheckDate", myCheckDate);

        return "redirect:..";
    }
}
