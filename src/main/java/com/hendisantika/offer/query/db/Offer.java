package com.hendisantika.offer.query.db;

import com.hendisantika.offer.query.OfferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-cqrs-event-sourcing-demo
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/02/22
 * Time: 20.22
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(
        name = "offer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "offer_uuid_uk",
                        columnNames = Offer.COLUMN_UUID
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
class Offer implements Serializable {
    static final String COLUMN_UUID = "uuid";
    private static final String SEQUENCE_GENERATOR = "offer_seq";
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_GENERATOR, allocationSize = 1)
    @GeneratedValue(generator = SEQUENCE_GENERATOR, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = COLUMN_UUID, nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "paid_by")
    private String paidBy;

    @Column(name = "status", nullable = false)
    private OfferStatus status;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "last_event_uuid", nullable = false)
    private UUID lastEventUUID;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return uuid.equals(offer.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
