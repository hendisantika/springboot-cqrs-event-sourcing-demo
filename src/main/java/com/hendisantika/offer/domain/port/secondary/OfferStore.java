package com.hendisantika.offer.domain.port.secondary;

import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.31
 * To change this template use File | Settings | File Templates.
 */
public interface OfferStore {
    void save(OfferEvent event);

    List<OfferEvent> findByOfferUUIDOrderByCreatedAt(UUID uuid);
}
