package com.hendisantika.offer.query.db;

import com.hendisantika.offer.query.OfferDto;
import com.hendisantika.offer.query.OfferQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.29
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class OfferQueryServiceDb implements OfferQueryService {
    private final OfferRepository offerRepository;
    private final OfferMapperDb offerMapper;

    @Override
    public Optional<OfferDto> findByOfferUUID(UUID uuid) {
        return offerRepository.findByUuid(uuid).map(offerMapper::toOfferDto);
    }
}
