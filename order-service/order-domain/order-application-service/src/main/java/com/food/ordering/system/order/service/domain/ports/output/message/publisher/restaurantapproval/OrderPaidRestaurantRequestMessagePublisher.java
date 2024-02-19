package com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

/**
 * So actually, we use a single domain event publisher interface with a publish method. However, we also give meaningful names for the specific publishers using different interfaces
 * so that we can understand why a publisher is used juste by the name, following the domain-driven design.
 * We also say that we used the strategy design pattern.
 */
public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {

}
