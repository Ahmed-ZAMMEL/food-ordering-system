package com.food.ordering.system.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    private ID id;

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    /*
     * equals and hashCode are the contracts that are used to compare the Java Objects.
     * Therefore, they are used to guarantee the object's uniqueness.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
