package br.com.nlw.events.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static Response createEvent(Map<String, Object> eventData) {
        return given()
                .contentType(ContentType.JSON)
                .body(eventData)
                .when()
                .post("/event");
    }
}
