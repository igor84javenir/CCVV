package fr.asigroup.ccvv.controller;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.pojo.NamesOfStartEndCities;
import fr.asigroup.ccvv.pojo.PathFinder;
import fr.asigroup.ccvv.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/find-path/")
public class TempFindPathController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/")
    public String findPath(Model model, @ModelAttribute("namesOfStartEndCitiesAttribute") NamesOfStartEndCities namesOfStartEndCities) {

        Iterable<City> cities = cityRepository.findAll();
        List<String> citiesNames = new ArrayList<>();

        for (City city : cities) {
            citiesNames.add(city.getName());
        }

        model.addAttribute("citiesNames", citiesNames);

        if (cityRepository.existsByName(namesOfStartEndCities.getStartCityName())
                && cityRepository.existsByName(namesOfStartEndCities.getDestinationCityName())) {

            PathFinder pathFinder = new PathFinder();
            Map<List<String>, Integer> path = pathFinder.findPath(namesOfStartEndCities.getStartCityName(), namesOfStartEndCities.getDestinationCityName());
            Map<List<String>, String> pathReadable = pathFinder.convertInHumanReadableDuration(path);
            model.addAttribute("pathReadable", pathReadable);

        }

        return "tempFindPathController";
    }

    @PostMapping("/find/")
    public String showPath(NamesOfStartEndCities namesOfStartEndCities,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("namesOfStartEndCitiesAttribute", namesOfStartEndCities);

        return "redirect:..";
    }
}
