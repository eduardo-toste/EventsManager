package br.com.nlw.events.steps;

import br.com.nlw.events.utils.ApiHelper;
import br.com.nlw.events.utils.DataHelper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class CreateEventSteps {

    private Response response;
    private Map<String, Object> eventData;

    @Given("que eu informo os dados válidos de um evento")
    public void queEuInformoOsDadosVálidosDeUmEvento() {
        eventData = DataHelper.EventData("Java Immersive", "Online",
                0, LocalDate.of(2025, 6, 6), LocalDate.of(2025, 6, 9),
                LocalTime.of(14, 30, 00), LocalTime.of(18, 30, 00));

        response = ApiHelper.createEvent(eventData);
    }

    @Then("deve me retornar os dados do evento criado")
    public void deveMeRetornarOsDadosDoEventoCriado() {
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is("Java Immersive"))
                .body("location", is("Online"))
                .body("price", is(0))
                .body("startDate", is("2025-06-06"))
                .body("endDate", is("2025-06-09"))
                .body("startTime", is("14:30:00"))
                .body("endTime", is("18:30:00"));
    }
}
