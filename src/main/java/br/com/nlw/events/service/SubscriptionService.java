package br.com.nlw.events.service;

import br.com.nlw.events.dto.SubscriptionResponse;
import br.com.nlw.events.exception.EventNotFoundException;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicadorNotFoundException;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.model.Subscription;
import br.com.nlw.events.model.User;
import br.com.nlw.events.repository.EventRepo;
import br.com.nlw.events.repository.SubscriptionRepo;
import br.com.nlw.events.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId){
        // Recuperar evento pelo nome
        Event evt =  eventRepo.findByPrettyName(eventName);

        if(evt == null){ //Caso alternativo 2
            throw new EventNotFoundException("Evento " + eventName + " não existe!");
        }
        User userRec = userRepo.findByEmail(user.getEmail());

        if(userRec == null){ //Caso alternativo 1
            userRec = userRepo.save(user);
        }

        User indicador = null;
        if(userId != null){
            indicador = userRepo.findById(userId).orElse(null);
            if(indicador == null){
                throw new UserIndicadorNotFoundException("Usuário " + userId + " indicador não existe!");
            }
        }

        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indicador);

        Subscription tmpSub = subscriptionRepo.findByEventAndSubscriber(evt, userRec);

        if(tmpSub != null){
            throw new SubscriptionConflictException("Já existe incrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
        }

        Subscription res = subscriptionRepo.save(subs);
        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getUserId());
    }
}
