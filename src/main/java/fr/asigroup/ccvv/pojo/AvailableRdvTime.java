package fr.asigroup.ccvv.pojo;

import java.time.LocalTime;

public class AvailableRdvTime {
    LocalTime time;
    boolean available;

    public AvailableRdvTime(LocalTime time, boolean available) {
        this.time = time;
        this.available = available;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "AvailableRdvTime{" +
                "time=" + time +
                ", available=" + available +
                '}';
    }
}
