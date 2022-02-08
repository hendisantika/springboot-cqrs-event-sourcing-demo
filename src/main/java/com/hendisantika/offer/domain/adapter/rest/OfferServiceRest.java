package com.hendisantika.offer.domain.adapter.rest;

import com.hendisantika.offer.domain.port.primary.OfferCommandService;
import com.hendisantika.offer.query.OfferQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
