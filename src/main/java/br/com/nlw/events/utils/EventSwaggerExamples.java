package br.com.nlw.events.utils;

public class EventSwaggerExamples {
    public static final String RESPONSE_200_GET_EVENTS = """
        [
          {
            "eventId": 6,
            "title": "CodeCraft Summit 2025",
            "prettyName": "codecraft-summit-2025",
            "location": "Online",
            "price": 0,
            "startDate": "2025-03-16",
            "endDate": "2025-03-18",
            "startTime": "19:00:00",
            "endTime": "21:00:00"
          },
          {
            "eventId": 7,
            "title": "Imersao Java",
            "prettyName": "imersao-java",
            "location": "Online",
            "price": 0,
            "startDate": "2025-06-01",
            "endDate": "2025-06-03",
            "startTime": "19:00:00",
            "endTime": "22:00:00"
          }
        ]
        """;

    public static final String RESPONSE_400_GET_EVENTS = """
        {
          "message": "Não existem eventos cadastrados."
        }
        """;

    public static final String INPUT_CREATE_EVENT = """
            {
              "title": "Nome Evento",
              "location": "Online",
              "price": 0,
              "startDate": "2025-06-06",
              "endDate": "2025-06-09",
              "startTime": "19:00:00",
              "endTime": "22:00:00"
            }
            """;

    public static final String RESPONSE_200_CREATE_EVENT = """
            {
                "eventId": 22,
                "title": "Nome Evento",
                "prettyName": "nome-evento",
                "location": "Online",
                "price": 0.0,
                "startDate": "2025-06-06",
                "endDate": "2025-06-09",
                "startTime": "19:00:00",
                "endTime": "22:00:00"
            }
            """;

    public static final String RESPONSE_409_CREATE_EVENT = """
            {
              "message": "Este evento já foi cadastrado"
            }
        """;

    public static final String RESPONSE_200_GET_EVENT_BY_PRETTYNAME = """
            {
                "eventId": 24,
                "title": "Nome Evento",
                "prettyName": "nome-evento",
                "location": "Online",
                "price": 0.0,
                "startDate": "2025-06-06",
                "endDate": "2025-06-09",
                "startTime": "19:00:00",
                "endTime": "22:00:00"
            }
        """;


}
