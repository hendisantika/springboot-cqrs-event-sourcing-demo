package com.hendisantika.offer.domain.event;

import com.hendisantika.offer.domain.infrastructure.event.BaseOfferEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 19.53
 * To change this template use File | Settings | File Templates.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class OfferCreatedEvent extends BaseOfferEvent {
    public static final String TYPE = "offer.created";

    UUID offerUUID;
    String productName;
    BigDecimal amount;
    LocalDateTime createdAt;
    String createdBy;

    public OfferCreatedEvent(UUID offerUUID, String productName, BigDecimal amount, LocalDateTime createdAt, String createdBy) {
        this.offerUUID = offerUUID;
        this.productName = productName;
        this.amount = amount;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public UUID offerUUID() {
        return offerUUID;
    }
}
