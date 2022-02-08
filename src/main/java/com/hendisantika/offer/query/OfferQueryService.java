package com.hendisantika.offer.query;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.22
 * To change this template use File | Settings | File Templates.
 */
public interface OfferQueryService {
    Optional<OfferDto> findByOfferUUID(UUID uuid);
}
