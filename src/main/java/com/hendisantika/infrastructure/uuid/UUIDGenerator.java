package com.hendisantika.infrastructure.uuid;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 19.55
 * To change this template use File | Settings | File Templates.
 */
@UtilityClass
public class UUIDGenerator {
    public UUID generate() {
        return UUID.randomUUID();
    }
}
