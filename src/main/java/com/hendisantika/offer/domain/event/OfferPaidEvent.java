package com.hendisantika.offer.domain.event;

import com.hendisantika.offer.domain.infrastructure.event.BaseOfferEvent;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.03
 * To change this template use File | Settings | File Templates.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class OfferPaidEvent extends BaseOfferEvent {
    public static final String TYPE = "offer.paid";

    UUID offerUUID;
    LocalDateTime paidAt;
    String paidBy;

    public OfferPaidEvent(UUID offerUUID, LocalDateTime paidAt, String paidBy) {
        this.offerUUID = offerUUID;
        this.paidAt = paidAt;
        this.paidBy = paidBy;
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
