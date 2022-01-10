package fr.asigroup.ccvv.repository;

import fr.asigroup.ccvv.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

//    Optional<City> findCityByName(String cityName);

    boolean existsByName(String cityName);
}
