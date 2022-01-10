package fr.asigroup.ccvv.pojo;

public class NamesOfStartEndCities {
    private String startCityName;
    private String destinationCityName;

    public NamesOfStartEndCities() {
    }

    public NamesOfStartEndCities(String startCityName, String destinationCityName) {
        this.startCityName = startCityName;
        this.destinationCityName = destinationCityName;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    @Override
    public String toString() {
        return "FindPathDTO{" +
                "startCityName='" + startCityName + '\'' +
                ", destinationCityName='" + destinationCityName + '\'' +
                '}';
    }
}
