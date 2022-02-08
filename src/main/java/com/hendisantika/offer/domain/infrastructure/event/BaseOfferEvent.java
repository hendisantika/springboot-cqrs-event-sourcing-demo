package com.hendisantika.offer.domain.infrastructure.event;

import com.hendisantika.infrastructure.uuid.UUIDGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 19.54
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseOfferEvent implements OfferEvent {
    UUID eventUUID;
    LocalDateTime eventTime;

    protected BaseOfferEvent() {
        this.eventUUID = UUIDGenerator.generate();
        this.eventTime = LocalDateTime.now();
    }

    @Override
    public UUID eventUUID() {
        return eventUUID;
    }

    @Override
    public LocalDateTime eventTime() {
        return eventTime;
    }
}
