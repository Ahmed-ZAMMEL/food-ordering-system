package com.food.ordering.system.order.service.domain.ports.input.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

/**
 * NOTE:
 * => Remember, each of these ports will have the corresponding adapters either in the domain layer or in one of the infrastructure layers.
 * => As we remember, we have two types of ports in hexagonal architecture. Input ports are the interfaces that are implemented in the domain layer and used by the clients of the domain layer.
 * The output ports are the interfaces that are implemented in the infrastructure like data access or messaging modules and used by the domain layer to reach to those infrastructure layers.
 */
public interface OrderApplicationService {
    /**
     * Create order.
     *
     * @param createOrderCommand order information.
     * @return order response.
     */
    /* NOTE: @Valid
     * => The @Valid annotation needs to be used here and not in the implementation as it is mentioned in the specification and methods preconditions.
     * As represented by parameter's constraints must not be strengthened in the subtypes as we see here.
     * If we use @Valid annotation in the implementation, we will get constraints' declaration exception, which is true, according to the specification.
     */
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    /**
     * Track the order, status.
     *
     * @param trackOrderQuery track query.
     * @return order track response.
     */
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
