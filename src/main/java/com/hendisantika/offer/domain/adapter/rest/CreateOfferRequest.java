package com.hendisantika.offer.domain.adapter.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.54
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
class CreateOfferRequest {
    @NotEmpty
    private String productName;
    @NotNull
    private BigDecimal amount;
}
