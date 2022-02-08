package com.hendisantika.offer.domain.adapter.primary.rest;

import com.hendisantika.offer.domain.command.CreateOfferCommand;
import com.hendisantika.offer.domain.command.UpdateOfferCommand;
import com.hendisantika.offer.domain.port.primary.OfferCommandService;
import com.hendisantika.offer.query.OfferQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.56
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
class OfferServiceRest {
    private final OfferCommandService offerCommandService;
    private final OfferQueryService offerQueryService;
    private final OfferMapperRest offerMapper;

    UUID createOffer(CreateOfferRequest request, String createdBy, LocalDateTime createdAt) {
        CreateOfferCommand command = offerMapper.toCreateOfferCommand(request, createdBy, createdAt);
        return offerCommandService.createOffer(command);
    }

    void updateOffer(UUID offerUUID, UpdateOfferRequest request, String updatedBy, LocalDateTime updatedAt) {
        UpdateOfferCommand command = offerMapper.toUpdateOfferCommand(request, offerUUID, updatedBy, updatedAt);
        offerCommandService.updateOffer(command);
    }

    Optional<OfferResponse> findOffer(UUID offerUUID) {
        return offerQueryService.findByOfferUUID(offerUUID).map(offerMapper::toOfferResponse);
    }
}
