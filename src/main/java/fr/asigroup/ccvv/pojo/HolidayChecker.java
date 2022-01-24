package fr.asigroup.ccvv.pojo;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class HolidayChecker {

    public String isHoliday(LocalDate localDate) {
        String isHoliday = "no";

        Map<String, LocalDate> holidaysOfYear = getHolidaysOfYear(localDate.getYear());

        for (Map.Entry<String, LocalDate> entry : holidaysOfYear.entrySet()) {
            if (localDate.equals(entry.getValue())) {
                isHoliday = entry.getKey();
            }
        }

        return isHoliday;
    }

    private Map<String, LocalDate> getHolidaysOfYear(int year) {
        Map<String, LocalDate> holidaysOfYear = new LinkedHashMap<>();

        LocalDate easter = getEasterDay(year);

        holidaysOfYear.put("Jour de l'An", LocalDate.parse(year + "-01-01"));
        holidaysOfYear.put("Dimanche de Pâques", easter);
        holidaysOfYear.put("Lundi de Pâques", easter.plusDays(1));
        holidaysOfYear.put("Fête du Travail", LocalDate.parse(year + "-05-01"));
        holidaysOfYear.put("Armistice 1945", LocalDate.parse(year + "-05-08"));
        holidaysOfYear.put("Jeudi Ascension", easter.plusDays(39));
        holidaysOfYear.put("Dimanche de Pentecôte", easter.plusDays(49));
        holidaysOfYear.put("Fête Nationale", LocalDate.parse(year + "-07-14"));
        holidaysOfYear.put("Assomption", LocalDate.parse(year + "-08-15"));
        holidaysOfYear.put("Toussaint", LocalDate.parse(year + "-11-01"));
        holidaysOfYear.put("Armistice 1918", LocalDate.parse(year + "-11-11"));
        holidaysOfYear.put("Noël", LocalDate.parse(year + "-12-25"));

        return holidaysOfYear;
    }

    private LocalDate getEasterDay(int year) {

        int n = year - 1900;
        int a = year % 19;
        int b = (11 * a + 4 - ((a * 7 + 1) / 19)) % 29;

        int easterOffset = 25 - b - ((n - b + 31 + (n / 4)) % 7);

        LocalDate easterDay = LocalDate.parse((year + "-03-31")).plusDays(easterOffset);

        return easterDay;
    }

}
