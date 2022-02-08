package com.hendisantika.infrastructure.db;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.49
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "event_descriptor")
@Getter
@NoArgsConstructor
public class EventDescriptor {

    @Id
    @GeneratedValue(generator = "event_descriptors_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "event_descriptors_seq", sequenceName = "event_descriptors_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 600)
    private String body;

    @Column(nullable = false, name = "occurred_at")
    private LocalDateTime occurredAt = LocalDateTime.now();

    @Column(nullable = false, length = 60)
    private String type;

    public EventDescriptor(String body, LocalDateTime occurredAt, String type) {
        this.body = body;
        this.occurredAt = occurredAt;
        this.type = type;
    }
}
