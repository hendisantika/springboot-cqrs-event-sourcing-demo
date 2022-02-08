package com.hendisantika.offer.domain.adapter.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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
}
