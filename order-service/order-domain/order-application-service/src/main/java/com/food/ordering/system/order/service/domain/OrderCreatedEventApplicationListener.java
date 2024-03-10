package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    /**
     * Publish created order event.
     * With the TransactionalEventListener annotation, this method will only be called when the method that publishes the application event publisher,
     * in the class ApplicationDomainEventPublisher, is completed, and the transaction is committed.
     * In the class OrderCreateCommandHandler, we call the publishEvent method com.food.ordering.system.order.service.domain.OrderCreateCommandHandler#applicationEventPublisher.
     * This listener, which is the process method, will only process when the createOrder method is completed and the transaction is committed, since we used TransactionalEventListener annotation.
     *
     * @param orderCreatedEvent created order event.
     */
    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    }
}
