package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * In this class we will use spring annotation, @service, and mark this class as Spring Bean.
 * As you see, we started using Spring Framework abilities on the application service.
 * NOTE:
 * ****** => We can use package-private access modifier instead of using public.
 * ********* We will expose the interface to the client of this domain layer, but actually,
 * ********* we will not give the details of the implementation. Here, having a package-private
 * ********* access modifier enough.
 */
@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    /**
     * We will delegate these two tasks to different classes to keep this service shorter.
     */

    private final OrderCreateCommandHandler orderCreateCommandController;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandController, OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandController = orderCreateCommandController;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandController.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
