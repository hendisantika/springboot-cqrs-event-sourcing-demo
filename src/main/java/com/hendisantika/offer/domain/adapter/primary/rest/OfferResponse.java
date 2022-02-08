package com.hendisantika.offer.domain.adapter.primary.rest;

import com.hendisantika.offer.query.OfferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.57
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
class OfferResponse {
    private UUID uuid;
    private String productName;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime paidAt;
    private String paidBy;
    private OfferStatus status;
    private Long version;
}
