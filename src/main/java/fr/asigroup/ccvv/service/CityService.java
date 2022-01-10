package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAll() throws CityNotFoundException {
        Iterable<City> citiesIterable = cityRepository.findAll();

        List<City> cities = new ArrayList<>();
        for (City c : citiesIterable) {
            if (c != null) {
                cities.add(c);
            }
        }

        if (!cities.isEmpty()) {
            return cities;
        } else {
            throw new CityNotFoundException("No cities found");
        }
    }
}
