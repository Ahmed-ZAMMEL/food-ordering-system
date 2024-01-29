package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

/**
 * With orderCreatedEvent, OrderPaidEvent and OrderCancelledEvent, we have a lot of code duplication.
 * We will fix that using inheritance.
 * This class will be abstract as we don't it to be created.
 */
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    // We won't create a builder for this event object since it contains only two fields. Let's create a constructor.
    public OrderEvent(Order order, ZonedDateTime createAt) {
        this.order = order;
        this.createdAt = createAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreateAt() {
        return createdAt;
    }
}
