package br.com.nlw.events.controller;

import br.com.nlw.events.dto.ErrorMessage;
import br.com.nlw.events.exception.EventConflictException;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nlw.events.utils.EventSwaggerExamples;
import java.util.List;

@RestController
@Tag(name = "Events")
public class EventController {

    @Autowired
    private EventService service;

    @Operation(summary = "Realiza a criação de um novo evento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = EventSwaggerExamples.RESPONSE_200_CREATE_EVENT))),
            @ApiResponse(responseCode = "409", description = "Evento já existente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = EventSwaggerExamples.RESPONSE_409_CREATE_EVENT))),
    })
    @PostMapping("/events")
    public ResponseEntity<?> addNewEvent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do evento a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "Exemplo de evento", value = EventSwaggerExamples.INPUT_CREATE_EVENT)
                    )
            )
            @RequestBody Event newEvent
    ) {
        try {
            Event createdEvent = service.addNewEvent(newEvent);
            return ResponseEntity.status(200).body(createdEvent);
        } catch (EventConflictException ex) {
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ErrorMessage("Erro ao processar a requisição"));
        }
    }

    @Operation(summary = "Realiza a busca de todos os eventos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = EventSwaggerExamples.RESPONSE_200_GET_EVENTS))),
            @ApiResponse(responseCode = "404", description = "Nenhum evento encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = EventSwaggerExamples.RESPONSE_400_GET_EVENTS)))
    })
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(){
        try {
            return ResponseEntity.ok(service.getAllEvents());
        } catch (EventNotFoundException ex){
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
        }
    }

    @Operation(summary = "Realiza a busca de um evento específico pelo seu Pretty Name", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = EventSwaggerExamples.RESPONSE_200_GET_EVENT_BY_PRETTYNAME))),
    })
    @GetMapping("/events/{prettyName}")
    public ResponseEntity<Event> getByPrettyName(@PathVariable String prettyName){
        Event evt = service.getByPrettyName(prettyName);
        return (evt != null) ? ResponseEntity.ok().body(evt) : ResponseEntity.notFound().build();
    }
}
