package com.hendisantika.offer.query.db;

import com.hendisantika.offer.domain.event.OfferCreatedEvent;
import com.hendisantika.offer.domain.event.OfferPaidEvent;
import com.hendisantika.offer.domain.event.OfferUpdatedEvent;
import com.hendisantika.offer.query.OfferStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.26
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class OfferReadModelStore {
    private final OfferRepository offerRepository;

    @Transactional
    public void createOffer(OfferCreatedEvent event) {
        Offer offer = new Offer();
        offer.setUuid(event.getOfferUUID());
        offer.setProductName(event.getProductName());
        offer.setAmount(event.getAmount());
        offer.setCreatedAt(event.getCreatedAt());
        offer.setCreatedBy(event.getCreatedBy());
        offer.setUpdatedAt(event.getCreatedAt());
        offer.setUpdatedBy(event.getCreatedBy());
        offer.setStatus(OfferStatus.NEW);
        offer.setLastEventUUID(event.eventUUID());
        offerRepository.saveAndFlush(offer);
        log.info("Offer read model for offer with UUID [{}] created", event.getOfferUUID());
    }

    @Transactional
    public void updateOffer(OfferUpdatedEvent event) {
        Offer offer = offerRepository.findByUuid(event.getOfferUUID()).orElseThrow();
        offer.setProductName(event.getProductName());
        offer.setAmount(event.getAmount());
        offer.setUpdatedAt(event.getUpdatedAt());
        offer.setUpdatedBy(event.getUpdatedBy());
        offer.setLastEventUUID(event.eventUUID());
        log.info("Updated offer read model of offer with UUID [{}]", event.getOfferUUID());
    }

    @Transactional
    public void markAsPaid(OfferPaidEvent event) {
        Offer offer = offerRepository.findByUuid(event.getOfferUUID()).orElseThrow();
        offer.setPaidAt(event.getPaidAt());
        offer.setPaidBy(event.getPaidBy());
        offer.setUpdatedAt(LocalDateTime.now());
        offer.setUpdatedBy(event.getPaidBy());
        offer.setLastEventUUID(event.eventUUID());
        offer.setStatus(OfferStatus.PAID);
        log.info("Offer read model with UUID [{}] marked as paid", event.getOfferUUID());
    }
}
