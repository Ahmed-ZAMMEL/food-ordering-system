package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<T> {
    //This attribute is final, so it should be set in the constructor.
    private final T value;

    //No need for public modifier since this constructor will be used only by subclasses.
    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    // As a best practice, equals and hashCode methods can be implemented.
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(value, baseId.value);
    }
}
