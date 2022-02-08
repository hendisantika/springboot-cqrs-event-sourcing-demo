package com.hendisantika.offer.domain.command;

import com.hendisantika.offer.domain.infrastructure.command.OfferDomainCommand;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Value
@Builder
public class CreateOfferCommand implements OfferDomainCommand {
    String productName;
    BigDecimal amount;
    LocalDateTime createdAt;
    String createdBy;
}
