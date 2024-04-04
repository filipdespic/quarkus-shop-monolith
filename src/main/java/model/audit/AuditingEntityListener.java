package model.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import model.abstr.AbstractEntity;

import java.time.Instant;

public class AuditingEntityListener {
    @PrePersist
    void preCreate(AbstractEntity auditable) {
        Instant now = Instant.now();
        auditable.setCreatedDate(now);
        auditable.setLastModifiedDate(now);
    }

    @PreUpdate
    void preUpdate(AbstractEntity auditable) {
        Instant now = Instant.now();
        auditable.setLastModifiedDate(now);
    }
}
