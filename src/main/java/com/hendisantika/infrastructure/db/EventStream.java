package com.hendisantika.infrastructure.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.50
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(
        name = "event_stream",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = EventStream.COLUMN_UUID, name = "uuid_uk")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class EventStream {
    static final String COLUMN_UUID = "uuid";

    @Id
    @GeneratedValue(generator = "event_stream_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "event_stream_seq", sequenceName = "event_stream_seq", allocationSize = 1)
    private Long id;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(nullable = false, name = COLUMN_UUID, length = 36)
    private UUID uuid;

    @Version
    @Column(nullable = false)
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<EventDescriptor> events = new ArrayList<>();

    public EventStream(UUID aggregateUUID) {
        this.uuid = aggregateUUID;
    }

    public void addEvents(List<EventDescriptor> events) {
        this.events.addAll(events);
    }

    public List<EventDescriptor> getEvents() {
        return events
                .stream()
                .sorted(comparing(EventDescriptor::getOccurredAt))
                .collect(Collectors.toList());
    }
}
