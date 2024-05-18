package com.rgr.webtransferback.repository.settings;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntityAuditable extends BaseEntity {
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityAuditable)) return false;
        if (!super.equals(o)) return false;
        BaseEntityAuditable that = (BaseEntityAuditable) o;
        return 
                createdAt.equals(that.createdAt) &&
                updatedAt.equals(that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdAt, updatedAt);
    }
}
