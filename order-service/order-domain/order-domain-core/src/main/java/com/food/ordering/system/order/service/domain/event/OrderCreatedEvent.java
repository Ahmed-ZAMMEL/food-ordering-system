package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

/**
 * The DomainEvent is the marker interface to mark the class as a domain event also to mark the entity class using the generic type
 */
public class OrderCreatedEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createAt;

    // We won't create a builder for this event object since it contains only two fields. Let's create a constructor.
    public OrderCreatedEvent(Order order, ZonedDateTime createAt) {
        this.order = order;
        this.createAt = createAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }
}
