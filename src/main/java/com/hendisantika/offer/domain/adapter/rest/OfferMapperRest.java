package com.hendisantika.offer.domain.adapter.rest;

import com.hendisantika.offer.domain.command.CreateOfferCommand;
import com.hendisantika.offer.domain.command.UpdateOfferCommand;
import com.hendisantika.offer.query.OfferDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
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
@Mapper
interface OfferMapperRest {
    CreateOfferCommand toCreateOfferCommand(CreateOfferRequest request, String createdBy, LocalDateTime createdAt);

    UpdateOfferCommand toUpdateOfferCommand(UpdateOfferRequest request, UUID offerUUID, String updatedBy, LocalDateTime updatedAt);

    OfferResponse toOfferResponse(OfferDto offerDto);
}
