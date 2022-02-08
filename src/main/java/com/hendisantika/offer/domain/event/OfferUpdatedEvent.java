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
 * Time: 19.57
 * To change this template use File | Settings | File Templates.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class OfferUpdatedEvent extends BaseOfferEvent {
    public static final String TYPE = "offer.updated";

    UUID offerUUID;
    String productName;
    BigDecimal amount;
    LocalDateTime updatedAt;
    String updatedBy;

    public OfferUpdatedEvent(UUID offerUUID, String productName, BigDecimal amount, LocalDateTime updatedAt, String updatedBy) {
        this.offerUUID = offerUUID;
        this.productName = productName;
        this.amount = amount;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
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
