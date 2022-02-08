package com.hendisantika.offer.domain;

import com.hendisantika.offer.domain.event.OfferCreatedEvent;
import com.hendisantika.offer.domain.event.OfferUpdatedEvent;
import com.hendisantika.offer.domain.infrastructure.event.OfferEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 19.51
 * To change this template use File | Settings | File Templates.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Offer {

    @Getter
    private final UUID uuid;
    @Getter
    private final List<OfferEvent> pendingEvents;
    private String productName;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime paidAt;
    private String paidBy;
    private OfferState state;

    static Offer from(UUID uuid) {
        return new Offer(uuid, new LinkedList<>());
    }

    static Offer from(UUID uuid, List<OfferEvent> events) {
        Offer offer = new Offer(uuid, new LinkedList<>());
        events.forEach(offer::applyEvent);
        return offer;
    }

    void create(String productName, BigDecimal amount, LocalDateTime createdAt, String createdBy) {
        addEvent(new OfferCreatedEvent(uuid, productName, amount, createdAt, createdBy));
    }

    void update(String productName, BigDecimal amount, LocalDateTime updatedAt, String updatedBy) {
        throwIfStateIs(OfferState.PAID, "Offer is in illegal state for update");
        addEvent(new OfferUpdatedEvent(uuid, productName, amount, updatedAt, updatedBy));
    }

    void markAsPaid(LocalDateTime paidAt, String paidBy) {
        throwIfStateIs(OfferState.PAID, "Offer has already been paid");
        addEvent(new OfferPaidEvent(uuid, paidAt, paidBy));
    }

    void markEventsAsCommitted() {
        pendingEvents.clear();
    }

    private void addEvent(OfferEvent event) {
        pendingEvents.add(event);
        applyEvent(event);
    }

    private void applyEvent(OfferEvent event) {
        if (event instanceof OfferCreatedEvent) {
            applyEvent((OfferCreatedEvent) event);
        } else if (event instanceof OfferUpdatedEvent) {
            applyEvent((OfferUpdatedEvent) event);
        } else if (event instanceof OfferPaidEvent) {
            applyEvent((OfferPaidEvent) event);
        }
    }

    private void applyEvent(OfferCreatedEvent event) {
        this.productName = event.getProductName();
        this.amount = event.getAmount();
        this.createdAt = event.getCreatedAt();
        this.createdBy = event.getCreatedBy();
        this.updatedAt = event.getCreatedAt();
        this.updatedBy = event.getCreatedBy();
        this.state = OfferState.NEW;
    }

    private void applyEvent(OfferUpdatedEvent event) {
        this.productName = event.getProductName();
        this.amount = event.getAmount();
        this.updatedAt = event.getUpdatedAt();
        this.updatedBy = event.getUpdatedBy();
    }

    private void applyEvent(OfferPaidEvent event) {
        this.paidAt = event.getPaidAt();
        this.paidBy = event.getPaidBy();
    }

    private void throwIfStateIs(OfferState unexpectedState, String msg) {
        if (state == unexpectedState) {
            throw new IllegalStateException(msg + (" UUID: " + uuid));
        }
    }
}
