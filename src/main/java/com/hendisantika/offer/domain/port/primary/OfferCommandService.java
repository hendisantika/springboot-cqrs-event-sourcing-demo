package com.hendisantika.offer.domain.port.primary;

import com.hendisantika.offer.domain.command.CreateOfferCommand;
import com.hendisantika.offer.domain.command.UpdateOfferCommand;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.32
 * To change this template use File | Settings | File Templates.
 */
public interface OfferCommandService {
    UUID createOffer(CreateOfferCommand command);

    void updateOffer(UpdateOfferCommand command);

    void markOfferAsPaid(MarkOfferAsPaidCommand command);
}
