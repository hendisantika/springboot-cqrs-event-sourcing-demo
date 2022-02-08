package com.hendisantika.infrastructure.event;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.41
 * To change this template use File | Settings | File Templates.
 */
public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
