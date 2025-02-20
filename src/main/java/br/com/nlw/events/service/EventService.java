package br.com.nlw.events.service;

import br.com.nlw.events.exception.EventConflictException;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    public Event addNewEvent(Event event){
        //Gerando o pretty name
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ", "-"));
        Event evtRec = eventRepo.findByPrettyName(event.getPrettyName());

        if(evtRec != null){
            throw new EventConflictException("Este evento já foi cadastrado");
        }

        return eventRepo.save(event);
    }

    public List<Event> getAllEvents(){
        List<Event> listEvtRec = (List<Event>) eventRepo.findAll();

        if(listEvtRec.size() == 0){
            throw new EventNotFoundException("Não existem eventos cadastrados");
        }

        return listEvtRec;
    }

    public Event getByPrettyName(String prettyName){
        return eventRepo.findByPrettyName(prettyName);
    }
}
