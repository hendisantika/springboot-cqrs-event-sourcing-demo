package com.hendisantika.offer.domain.command;

import com.hendisantika.offer.domain.infrastructure.command.OfferDomainCommand;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.37
 * To change this template use File | Settings | File Templates.
 */
@Value
@Builder
public class MarkOfferAsPaidCommand implements OfferDomainCommand {
    UUID offerUUID;
    LocalDateTime paidAt;
    String paidBy;
}
