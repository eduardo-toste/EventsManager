package br.com.nlw.events.repository;

import br.com.nlw.events.dto.SubscriptionRankingByUser;
import br.com.nlw.events.dto.SubscriptionRankingItem;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.model.Subscription;
import br.com.nlw.events.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
    public Subscription findByEventAndSubscriber(Event event, User user);

    @Query(value = "SELECT " +
            "    COUNT(subscription_number) AS quantidade, " +
            "    indication_user_id, " +
            "    user_name " +
            "FROM db_events.tbl_subscription as s " +
            "INNER JOIN db_events.tbl_user as u ON s.indication_user_id = u.user_id " +
            "WHERE indication_user_id IS NOT NULL " +
            "AND event_id = :eventId " +
            "GROUP BY indication_user_id " +
            "ORDER BY quantidade DESC ", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Integer eventId);
}
