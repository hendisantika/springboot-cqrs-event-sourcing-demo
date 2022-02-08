package com.hendisantika.offer.query;

import lombok.Data;

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
 * Time: 20.21
 * To change this template use File | Settings | File Templates.
 */
@Data
public class OfferDto {
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
