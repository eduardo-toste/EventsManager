package br.com.nlw.events.controller;

import br.com.nlw.events.dto.ErrorMessage;
import br.com.nlw.events.dto.SubscriptionResponse;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicadorNotFoundException;
import br.com.nlw.events.model.User;
import br.com.nlw.events.service.SubscriptionService;
import br.com.nlw.events.utils.EventSwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nlw.events.utils.SubscriptionSwaggerExamples;

@RestController
@Tag(name = "Subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @Operation(summary = "Realiza a inscrição em um evento específico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscrição realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_200_CREATE_SUBSCRIPTION))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_404_CREATE_SUBSCRIPTION))),
            @ApiResponse(responseCode = "409", description = "Usuário já inscrito no evento",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_409_CREATE_SUBSCRIPTION)))
    })
    @PostMapping("/subscription/{prettyName}")
    public ResponseEntity<?> createSubscription(
            @PathVariable String prettyName,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da inscrição a ser criada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "Exemplo de inscrição", value = SubscriptionSwaggerExamples.INPUT_CREATE_SUBSCRIPTION)
                    )
            )
            @RequestBody User subscriber) {
        return handleSubscription(prettyName, subscriber, null);
    }

    @Operation(summary = "Realiza a inscrição em um evento específico para um usuário indicado", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscrição realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_200_CREATE_SUBSCRIPTION))),
            @ApiResponse(responseCode = "404", description = "Evento ou usuário indicador não encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_404_CREATE_SUBSCRIPTION))),
            @ApiResponse(responseCode = "409", description = "Usuário já inscrito no evento",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_409_CREATE_SUBSCRIPTION)))
    })
    @PostMapping("/subscription/{prettyName}/{userId}")
    public ResponseEntity<?> createSubscriptionWithUserId(
            @PathVariable String prettyName,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da inscrição a ser criada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "Exemplo de inscrição", value = SubscriptionSwaggerExamples.INPUT_CREATE_SUBSCRIPTION)
                    )
            )
            @RequestBody User subscriber,
            @PathVariable Integer userId) {
        return handleSubscription(prettyName, subscriber, userId);
    }

    private ResponseEntity<?> handleSubscription(String prettyName, User subscriber, Integer userId) {
        try {
            SubscriptionResponse res = service.createNewSubscription(prettyName, subscriber, userId);
            if (res != null) {
                return ResponseEntity.ok(res);
            }
        } catch (EventNotFoundException | UserIndicadorNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        } catch (SubscriptionConflictException ex) {
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Busca o ranking geral de indicações de um evento específico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de ranking realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_200_GET_RANKING))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_404_GET_RANKING))),
    })
    @GetMapping("/subscription/{prettyName}/ranking")
    public ResponseEntity<?> generateRankingByEvent(@PathVariable String prettyName){
        try {
            return ResponseEntity.ok(service.getCompleteRanking(prettyName).subList(0,3));
        } catch (EventNotFoundException ex) {
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }

    @Operation(summary = "Busca o ranking de um usuário a partir de suas indicações em um evento específico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de ranking realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_200_GET_RANKING_BY_ID))),
            @ApiResponse(responseCode = "404", description = "Evento ou usuário indicador não encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SubscriptionSwaggerExamples.RESPONSE_404_GET_RANKING_BY_ID))),
    })
    @GetMapping("/subscription/{prettyName}/ranking/{userId}")
    public ResponseEntity<?> generateRankingByEventAndUser(@PathVariable String prettyName, @PathVariable Integer userId){
        try {
            return ResponseEntity.ok(service.getRankingByUser(prettyName, userId));
        } catch (Exception ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }
}
