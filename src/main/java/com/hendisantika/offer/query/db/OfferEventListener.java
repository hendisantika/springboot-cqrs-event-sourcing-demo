package com.hendisantika.offer.query.db;

import com.hendisantika.offer.domain.event.OfferCreatedEvent;
import com.hendisantika.offer.domain.event.OfferPaidEvent;
import com.hendisantika.offer.domain.event.OfferUpdatedEvent;
import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class OfferEventListener {
    private final OfferReadModelStore readModelStore;

    @EventListener(classes = {OfferEvent.class})
    public void listenOn(OfferEvent event) {
        log.info("Event received: [{}] with UUID [{}] created at [{}]", event.getClass(), event.eventUUID(), event.eventTime());
        if (event instanceof OfferCreatedEvent) {
            readModelStore.createOffer((OfferCreatedEvent) event);
        } else if (event instanceof OfferUpdatedEvent) {
            readModelStore.updateOffer((OfferUpdatedEvent) event);
        } else if (event instanceof OfferPaidEvent) {
            readModelStore.markAsPaid((OfferPaidEvent) event);
        } else {
            throw new IllegalArgumentException(String.format("Event with class [%s] not handled in [%s]", event.getClass(), getClass()));
        }
    }
}
