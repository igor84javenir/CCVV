package fr.asigroup.ccvv.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
public class City{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private List<Itinerary> itineraries;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(Long id, String name, List<Itinerary> itineraries) {
        this.id = id;
        this.name = name;
        this.itineraries = itineraries;
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, List<Itinerary> itineraries) {
        this.name = name;
        this.itineraries = itineraries;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itineraries=" + itineraries +
                '}';
    }
}

