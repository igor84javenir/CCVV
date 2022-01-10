package fr.asigroup.ccvv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itineraries")
public class Itinerary implements Comparable<Itinerary>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_direction")
    private String nameOfDirection;

    @Column(name = "duration_of_travel")
    private Integer durationOfTravel;

    public Itinerary() {
    }

    public Itinerary(String nameOfDirection, Integer durationOfTravel) {
        this.nameOfDirection = nameOfDirection;
        this.durationOfTravel = durationOfTravel;
    }

    public String getNameOfDirection() {
        return nameOfDirection;
    }

    public void setNameOfDirection(String nameOfDirection) {
        this.nameOfDirection = nameOfDirection;
    }

    public int getDurationOfTravel() {
        return durationOfTravel;
    }

    public void setDurationOfTravel(Integer durationOfTravel) {
        this.durationOfTravel = durationOfTravel;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "nameOfDirection='" + nameOfDirection + '\'' +
                ", durationOfTravel=" + durationOfTravel +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((durationOfTravel == null) ? 0 : durationOfTravel.hashCode());
        result = prime * result + ((nameOfDirection == null) ? 0 : nameOfDirection.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Itinerary other = (Itinerary) obj;
        if (durationOfTravel == null) {
            if (other.durationOfTravel != null)
                return false;
        } else if (!durationOfTravel.equals(other.durationOfTravel))
            return false;
        if (nameOfDirection == null) {
            if (other.nameOfDirection != null)
                return false;
        } else if (!nameOfDirection.equals(other.nameOfDirection))
            return false;
        return true;
    }

    @Override
    public int compareTo(Itinerary itinerary) {
        if (this.durationOfTravel < itinerary.durationOfTravel)
            return -1;
        else if (this.durationOfTravel > itinerary.durationOfTravel)
            return 1;
        else
            return this.getNameOfDirection().compareTo(itinerary.getNameOfDirection());
    }

}