package com.food.ordering.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * We use Long as ID type, and not UUID, because the uniqueness of order item is only important in
 * the aggregates, so we don't need a UUID here
 */
public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
