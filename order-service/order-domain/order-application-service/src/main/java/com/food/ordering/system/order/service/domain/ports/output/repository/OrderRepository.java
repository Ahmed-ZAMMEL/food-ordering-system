package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    /* NOTE:
     * Here the order class is the entity class that we created in the domain core module. So as we see, we passed the domain entity to do repositories, and it'll be the repository implementation's responsibility to convert this order entity objects into a JPA entity objects and save it into the database.
     */
    Order save(Order order);

    /* NOTE:
     * => We may or may not find an order with that tracking id, so we use an Optional.
     */
    Optional<Order> findByTrackingId(TrackingId trackingId);
}
