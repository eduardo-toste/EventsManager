package br.com.nlw.events.service;

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

    public Subscription createNewSubscription(String eventName, User user){
        // Recuperar evento pelo nome
        Event evt =  eventRepo.findByPrettyName(eventName);
        User userRec = userRepo.findByEmail(user.getEmail());

        if(userRec == null){
            userRec = userRepo.save(userRec);
        }

        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);

        Subscription res = subscriptionRepo.save(subs);
        return res;
    }
}
