package com.hendisantika.offer.query.db;

import com.hendisantika.offer.query.OfferDto;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.27
 * To change this template use File | Settings | File Templates.
 */
@Mapper
interface OfferMapperDb {
    OfferDto toOfferDto(Offer offer);
}
