package com.hendisantika.offer.domain.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hendisantika.offer.domain.event.OfferCreatedEvent;
import com.hendisantika.offer.domain.event.OfferUpdatedEvent;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 19.52
 * To change this template use File | Settings | File Templates.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = OfferCreatedEvent.TYPE, value = OfferCreatedEvent.class),
        @JsonSubTypes.Type(name = OfferUpdatedEvent.TYPE, value = OfferUpdatedEvent.class),
        @JsonSubTypes.Type(name = OfferPaidEvent.TYPE, value = OfferPaidEvent.class)
})
public interface OfferEvent extends DomainEvent {
    /**
     * @return uuid of an offer aggregate
     */
    UUID offerUUID();
}
