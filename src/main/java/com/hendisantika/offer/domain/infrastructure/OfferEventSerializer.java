package com.hendisantika.offer.domain.infrastructure;

import com.hendisantika.infrastructure.db.EventDescriptor;
import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.48
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class OfferEventSerializer {
    private final ObjectMapper om;

    public EventDescriptor serialize(OfferEvent event) {
        return new EventDescriptor(om.writeValueAsString(event), event.eventTime(), event.type());
    }

    public OfferEvent deserialize(EventDescriptor eventDescriptor) {
        return om.readValue(eventDescriptor.getBody(), OfferEvent.class);
    }
}
