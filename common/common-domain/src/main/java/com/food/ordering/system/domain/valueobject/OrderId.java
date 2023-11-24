package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/*
 * The OrderId class was created her in the common domain
 * because we will need it for "Order" and "OrderItem" entities, and also for "PaymentService"
 * and "RestaurantService", so it is a common value object.
 */
public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }

}
