package br.com.nlw.events.utils;

public class SubscriptionSwaggerExamples {
    public static final String RESPONSE_200_CREATE_SUBSCRIPTION = """
            {
                "subscriptionNumber": 24,
                "designation": "http://codecraft.com/subscription/codecraft-summit-2025/23"
            }
        """;

    public static final String RESPONSE_404_CREATE_SUBSCRIPTION = """
            {
              "message": "Este evento não existe"
            }
        """;

    public static final String RESPONSE_409_CREATE_SUBSCRIPTION = """
            {
              "message": "Usuário já inscrito no evento"
            }
        """;

    public static final String INPUT_CREATE_SUBSCRIPTION = """
            {
                "name": "Seu Usuario",
                "email": "seu@usuario.com"
            }
        """;

    public static final String RESPONSE_200_GET_RANKING = """
            [
                {
                    "subscribers": 8,
                    "userId": 1,
                    "name": "Usuario1"
                },
                {
                    "subscribers": 3,
                    "userId": 3,
                    "name": "Usuario2"
                }
            ]
        """;

    public static final String RESPONSE_404_GET_RANKING = """
            {
                "message": "Ranking do evento não existe"
            }
        """;

    public static final String RESPONSE_200_GET_RANKING_BY_ID = """
            {
                "item": {
                    "subscribers": 8,
                    "userId": 1,
                    "name": "Eduardo"
                },
                "position": 1
            }
        """;

    public static final String RESPONSE_404_GET_RANKING_BY_ID = """
            {
                "message": "Ranking do evento ou usuário não encontrado"
            }
        """;
}
