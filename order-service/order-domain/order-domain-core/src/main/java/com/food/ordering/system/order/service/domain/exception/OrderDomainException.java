package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * The OrderDomainException wraps all checked standard Java exception and enriches them with a custom error message.
 * In case the business logic (order-domain-core, order service) fails, we will send appropriate error messages to the customers.
 */
public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
