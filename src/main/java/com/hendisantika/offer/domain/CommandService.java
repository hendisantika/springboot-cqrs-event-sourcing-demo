package com.hendisantika.offer.domain;

import com.hendisantika.infrastructure.uuid.UUIDGenerator;
import com.hendisantika.offer.domain.command.CreateOfferCommand;
import com.hendisantika.offer.domain.command.MarkOfferAsPaidCommand;
import com.hendisantika.offer.domain.command.UpdateOfferCommand;
import com.hendisantika.offer.domain.port.primary.OfferCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.45
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class CommandService implements OfferCommandService {
    private final OfferService offerService;

    @Override
    public UUID createOffer(CreateOfferCommand command) {
        UUID offerUUID = UUIDGenerator.generate();
        Offer offer = Offer.from(offerUUID);
        offer.create(command.getProductName(), command.getAmount(), command.getCreatedAt(), command.getCreatedBy());
        store(offer);
        log.info("Created new offer with UUID [{}]", offerUUID);
        return offerUUID;
    }

    @Override
    public void updateOffer(UpdateOfferCommand command) {
        Offer offer = loadOffer(command.getOfferUUID());
        offer.update(command.getProductName(), command.getAmount(), command.getUpdatedAt(), command.getUpdatedBy());
        store(offer);
        log.info("Updated offer with UUID [{}]", command.getOfferUUID());
    }

    @Override
    public void markOfferAsPaid(MarkOfferAsPaidCommand command) {
        Offer offer = loadOffer(command.getOfferUUID());
        offer.markAsPaid(command.getPaidAt(), command.getPaidBy());
        store(offer);
        log.info("Offer with UUID [{}] mark as paid", command.getOfferUUID());
    }

    private Offer loadOffer(UUID uuid) {
        return offerService.load(uuid);
    }

    private void store(Offer offer) {
        offerService.store(offer);
    }
}
