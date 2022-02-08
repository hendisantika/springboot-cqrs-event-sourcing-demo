package com.hendisantika.offer.domain.adapter.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 09/02/22
 * Time: 05.58
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
class OfferController {
    static final String OFFERS_URL = "/api/offers";
    private static final String OFFER_PATH_PARAM = "/{offerUUID}";
    private static final String OFFER_URL = OFFERS_URL + OFFER_PATH_PARAM;
    private final OfferServiceRest offerService;

    @PostMapping(
            path = OFFERS_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Void> createOffer(@RequestBody @Valid CreateOfferRequest request) {
        log.info("POST request on [{}]", OFFERS_URL);
        String createdBy = "rest-api-user"; // Can be replaced with Spring Security user
        UUID offerUUID = offerService.createOffer(request, createdBy, LocalDateTime.now());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(OFFER_PATH_PARAM).buildAndExpand(offerUUID).toUri();
        return ResponseEntity.created(location).build();
    }
}
