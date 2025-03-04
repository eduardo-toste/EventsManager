package br.com.nlw.events.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DataHelper {
    public static Map<String, Object> EventData(String title, String location,
                                                Float price, LocalDate startDate,
                                                LocalDate endDate, LocalTime startTime,
                                                LocalTime endTime) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("title", title);
        eventData.put("location", location);
        eventData.put("price", price);
        eventData.put("startDate", startDate.format(dateFormatter));
        eventData.put("endDate", endDate.format(dateFormatter));
        eventData.put("startTime", startTime.format(timeFormatter));
        eventData.put("endTime", endTime.format(timeFormatter));

        return eventData;
    }
}
