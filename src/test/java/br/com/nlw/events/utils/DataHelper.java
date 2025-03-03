package br.com.nlw.events.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class DataHelper {
    public static Map<String, Object> EventData(String title, String location,
                                              int price, LocalDate startDate,
                                              LocalDate endDate, LocalTime startTime,
                                              LocalTime endTime) {

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("title", title);
        eventData.put("location", location);
        eventData.put("price", price);
        eventData.put("startDate", startDate);
        eventData.put("endDate", endDate);
        eventData.put("startTime", startTime);
        eventData.put("endTime", endTime);

        return eventData;
    }
}
