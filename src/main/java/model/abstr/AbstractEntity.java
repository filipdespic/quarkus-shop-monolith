package model.abstr;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.audit.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

}
