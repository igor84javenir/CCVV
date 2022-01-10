package fr.asigroup.ccvv.pojo;

import fr.asigroup.ccvv.SpringContext;
import fr.asigroup.ccvv.entity.City;
import fr.asigroup.ccvv.entity.Itinerary;
import fr.asigroup.ccvv.repository.CityRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

@Component
public class PathFinder {

    public Map<List<String>,Integer> findPath (String startCity, String destinationCity) {

        CityRepository cityRepository = SpringContext.getBean(CityRepository.class);

        Graph graph = new Graph();

        List<City> cities = (List<City>) cityRepository.findAll();

        return graph.getShortestPath(startCity, destinationCity, cities);
    }

    public Map<List<String>,String> convertInHumanReadableDuration(Map<List<String>,Integer> path) {
        Map<List<String>,String> mapWithHumanReadableDuration = new HashMap<>();

        StringBuilder humanReadableDuration = new StringBuilder();
        for (Map.Entry<List<String>,Integer> element : path.entrySet()) {
            if (element.getValue() < 60) {
                humanReadableDuration.append(element.getValue()).append(" minute(s)");
            } else {
                Integer hours = element.getValue() / 60;
                humanReadableDuration.append(hours).append(" heure(s) et ");

                Integer minutes = element.getValue() - hours * 60;
                humanReadableDuration.append(minutes).append(" minute(s)");
            }
            mapWithHumanReadableDuration.put(element.getKey(), humanReadableDuration.toString());
        }

        return mapWithHumanReadableDuration;
    }


//    public static void main(String[] args) {
//        CityRepository cityRepository = SpringContext.getBean(CityRepository.class);
//
//        Graph graph = new Graph();
//
//        List<City> cities = (List<City>) cityRepository.findAll();
//
//        Map<List<String>,Integer> itinerary = graph.getShortestPath("Paris", "Berlin", cities);
//
//        System.out.println(itinerary);
//
//    }

}

class Graph {

    private final Map<String, List<Itinerary>> allItineraries;

    public Graph() {
        this.allItineraries = new HashMap<String, List<Itinerary>>();
    }

    public void addItineraries(String cityName, List<Itinerary> itinerariesOfCity) {
        this.allItineraries.put(cityName, itinerariesOfCity);
    }

    public Map<List<String>, Integer> getShortestPath(String startCityName, String destinationCityName, List<City> cities) {
        if (cities.stream()
                .noneMatch(x -> Objects.equals(x.getName(), startCityName)) || cities.stream().noneMatch(x -> Objects.equals(x.getName(), destinationCityName))) {
            return Map.of(List.of("Error, no such city or itinerary"), -1);
        }

        for (City c : cities) {
            addItineraries(c.getName(), c.getItineraries());
        }

        List<String> shortestPathWithoutDuration = getShortestPathWithoutDuration(startCityName, destinationCityName);
        Collections.reverse(shortestPathWithoutDuration);

        shortestPathWithoutDuration.add(0, startCityName);

        int travelTime = 0;
        for (int i = 0; i < shortestPathWithoutDuration.size() - 1; i++) {
            String nameOfStartCity = shortestPathWithoutDuration.get(i);
            String nameOfDestinationCity = shortestPathWithoutDuration.get(i + 1);
            City startCity = new City();

            for (City c : cities) {
                if (nameOfStartCity.equals(c.getName())) {
                    startCity = c;
                    break;
                }
            }

            for (Itinerary itineraryOfStartCity : startCity.getItineraries()) {
                if (nameOfDestinationCity.equals(itineraryOfStartCity.getNameOfDirection())) {
                    travelTime = travelTime + itineraryOfStartCity.getDurationOfTravel();
                    break;
                }
            }
        }

        Map<List<String>, Integer> shortestPath = new HashMap<>();
        shortestPath.put(shortestPathWithoutDuration, travelTime);

        return shortestPath;
    }

    private List<String> getShortestPathWithoutDuration(String startCityName, String destinationCityName) {
        final Map<String, Integer> distances = new HashMap<>();
        final Map<String, Itinerary> previous = new HashMap<>();
        PriorityQueue<Itinerary> nodes = new PriorityQueue<>();

        for(String cityName : allItineraries.keySet()) {
            if (startCityName.equals(cityName)) {
                distances.put(cityName, 0);
                nodes.add(new Itinerary(cityName, 0));
            } else {
                distances.put(cityName, Integer.MAX_VALUE);
                nodes.add(new Itinerary(cityName, Integer.MAX_VALUE));
            }
            previous.put(cityName, null);
        }

        while (!nodes.isEmpty()) {
            Itinerary smallest = nodes.poll();
            if (destinationCityName.equals(smallest.getNameOfDirection())) {
                final List<String> path = new ArrayList<>();
                while (previous.get(smallest.getNameOfDirection()) != null) {
                    path.add(smallest.getNameOfDirection());
                    smallest = previous.get(smallest.getNameOfDirection());
                }
                return path;
            }

            if (distances.get(smallest.getNameOfDirection()) == Integer.MAX_VALUE) {
                break;
            }

            for (Itinerary neighbor : allItineraries.get(smallest.getNameOfDirection())) {
                Integer alt = distances.get(smallest.getNameOfDirection()) + neighbor.getDurationOfTravel();
                if (alt < distances.get(neighbor.getNameOfDirection())) {
                    distances.put(neighbor.getNameOfDirection(), alt);
                    previous.put(neighbor.getNameOfDirection(), smallest);

                    for (Itinerary n : nodes) {
                        if (Objects.equals(n.getNameOfDirection(), neighbor.getNameOfDirection())) {
                            nodes.remove(n);
                            n.setDurationOfTravel(alt);
                            nodes.add(n);
                            break;
                        }
                    }
                }
            }
        }

        return new ArrayList<String>(distances.keySet());
    }
}
