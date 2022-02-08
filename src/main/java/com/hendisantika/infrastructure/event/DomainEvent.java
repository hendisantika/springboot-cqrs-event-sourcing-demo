package com.hendisantika.infrastructure.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.05
 * To change this template use File | Settings | File Templates.
 */
public interface DomainEvent {
    /**
     * @return UUID of an event
     */
    UUID eventUUID();

    /**
     * @return time of an event
     */
    LocalDateTime eventTime();

    /**
     * @return type of an event
     */
    String type();
}