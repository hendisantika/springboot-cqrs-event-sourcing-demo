package com.hendisantika.offer.domain;

import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import com.hendisantika.offer.domain.port.secondary.OfferStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.30
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class OfferService {
    private final OfferStore offerStore;
    private final DomainEventPublisher eventPublisher;

    void store(Offer offer) {
        List<OfferEvent> pendingEvents = offer.getPendingEvents();
        int noOfEvents = pendingEvents.size();
        pendingEvents.forEach(this::saveAndPublish);
        offer.markEventsAsCommitted();
        log.info("Offer with UUID [{}] stored, [{}] pending events saved and published", offer.getUuid(), noOfEvents);
    }

    Offer load(UUID uuid) {
        List<OfferEvent> events = offerStore.findByOfferUUIDOrderByCreatedAt(uuid);
        if (events.isEmpty()) {
            throw new OfferNotFoundException(String.format("Offer with UUID [%s] not found", uuid));
        }
        log.info("Offer with UUID [{}] loaded from store", uuid);
        return Offer.from(uuid, events);
    }
}
