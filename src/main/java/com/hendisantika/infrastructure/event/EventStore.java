package com.hendisantika.infrastructure.event;

import com.hendisantika.infrastructure.db.EventStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.51
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface EventStore extends JpaRepository<EventStream, Long> {
    Optional<EventStream> findByUuid(UUID uuid);
}
