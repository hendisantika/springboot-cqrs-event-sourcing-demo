package com.hendisantika.offer.domain.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.infrastructure.db.EventDescriptor;
import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        try {
            return new EventDescriptor(om.writeValueAsString(event), event.eventTime(), event.type());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
