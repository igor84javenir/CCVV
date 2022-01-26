package fr.asigroup.ccvv.pojo;

import fr.asigroup.ccvv.entity.DateCalendrier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class MonthCalendarGenerator {
    HolidayChecker holidayChecker = new HolidayChecker();

    public Map<LocalDate, DateCalendrier> getMonthCalendar(int monthOffset) {
        //Map<LocalDate, Map<String, String>> monthCalendar = new LinkedHashMap<>();
        Map<LocalDate, DateCalendrier> monthCalendar = new LinkedHashMap<>();

        LocalDate localDate = LocalDate.now();

        localDate = localDate.plusMonths(monthOffset);

        // Get first day of month
        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());

        //Get first day week
        localDate = localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1);

        for (int i = 0; i < 42; i++) {
            String availability = getAvailability(localDate);
            String classeCSS = "";
            String Texte = "";
            boolean cliquable = false;

            if (isWeekEnd(localDate)) {
                // Week-end
                classeCSS += "weekend ";
                Texte += "";
            }

            if (isFerie(localDate)) {
                // Férie
                classeCSS += "ferie ";
                Texte += getJourFerie(localDate);
            }

            if (isDisponible(localDate)) {
                // Diponibilité
                classeCSS += "disponible cliquable ";
                Texte += "";
                cliquable = true;
            }
            monthCalendar.put(localDate, new DateCalendrier(Texte, classeCSS, cliquable));

            localDate = localDate.plusDays(1);
        }

        System.out.println(monthCalendar);
        return monthCalendar;
    }

    public String getNameOfMonth(int monthOffset) {
        return LocalDate.now()
                .getMonth()
                .plus(monthOffset)
                .getDisplayName(TextStyle.FULL, Locale.FRANCE)
                .toUpperCase(Locale.ROOT);
    }

    public int getYear(int monthOffset) {
        return LocalDate.now()
                .plusMonths(monthOffset)
                .getYear();
    }
    private String getAvailability (LocalDate localDate) {
        String availability;

        if (!"no".equals(holidayChecker.isHoliday(localDate))) {
            availability = holidayChecker.isHoliday(localDate);
        } else if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            availability = "WEEK-END";
        } else {
            availability = "Available";
        }

        return availability;
    }

    private boolean isWeekEnd(LocalDate localDate) {
        return (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    private boolean isFerie(LocalDate localDate) {
        return !"no".equals(holidayChecker.isHoliday(localDate));
    }

    private boolean isDisponible(LocalDate localDate) {
        if (isWeekEnd(localDate) || isFerie(localDate))
            return false;
        else



            return true;
    }

    private String getJourFerie(LocalDate localDate) {
        return holidayChecker.isHoliday(localDate);
    }
}


//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
//import java.time.temporal.TemporalAdjusters;
//import java.time.temporal.WeekFields;
//import java.util.LinkedHashMap;
//import java.util.Locale;
//import java.util.Map;
//
//public class MonthCalendarGenerator {
//    HolidayChecker holidayChecker = new HolidayChecker();
//
//    public Map<LocalDate, Map<String, String>> getMonthCalendar(int monthOffset) {
//        Map<LocalDate, Map<String, String>> monthCalendar = new LinkedHashMap<>();
//
//        LocalDate localDate = LocalDate.now();
//
//        localDate = localDate.plusMonths(monthOffset);
//
//        // Get first day of month
//        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
//
//        //Get first day of week
//        localDate = localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1);
//
//        for (int i = 0; i < 42; i++) {
//            String availability = getAvailability(localDate);
//
//            if (!"Available".equals(availability)) {
//                monthCalendar.put(localDate, Map.of(availability, ""));
//            } else {
//                monthCalendar.put(localDate, Map.of("Disponible", "RDV prevu ou vide"));
//            }
//
//            localDate = localDate.plusDays(1);
//        }
//
//        return monthCalendar;
//    }
//
//    public String getNameOfMonth(int monthOffset) {
//        return LocalDate.now()
//                .getMonth()
//                .plus(monthOffset)
//                .getDisplayName(TextStyle.FULL, Locale.FRANCE)
//                .toUpperCase(Locale.ROOT);
//    }
//
//    private String getAvailability (LocalDate localDate) {
//        String availability;
//
//        if (!"no".equals(holidayChecker.isHoliday(localDate))) {
//            availability = holidayChecker.isHoliday(localDate);
//        } else if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
//            availability = "WEEK-END";
//        } else {
//            availability = "Available";
//        }
//
//        return availability;
//    }
//}