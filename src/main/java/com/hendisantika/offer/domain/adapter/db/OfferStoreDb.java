package com.hendisantika.offer.domain.adapter.db;

import com.hendisantika.infrastructure.db.EventDescriptor;
import com.hendisantika.infrastructure.event.EventStore;
import com.hendisantika.offer.domain.infrastructure.OfferEventSerializer;
import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import com.hendisantika.offer.domain.port.secondary.OfferStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/02/22
 * Time: 06.00
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class OfferStoreDb implements OfferStore {
    private final OfferEventSerializer eventSerializer;
    private final EventStore eventStore;

    @Override
    public void save(OfferEvent event) {
        EventDescriptor eventDescriptor = eventSerializer.serialize(event);
        eventStore.saveEvents(event.offerUUID(), Collections.singletonList(eventDescriptor));
    }
}
