package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    /**
     * NOTES:
     * => We return the domain events from the domain service, that means the event firing process will be on the caller service that be the application service.
     *    This is an approach that we prefer for the domain events handling process. So the events will still be created in the domain core either in entity or in domain service.
     *    However, the event firing will be in the application service. We do this BEFORE FIRING AN EVENT, THE UNDERLYING BUSINESS OPERATION SHOULD BE PERSISTED INTO
     *    THE DATABASE BECAUSE IF WE FIRE AN EVENT AND PERSIST OPERATION PHASE LATER, WE WILL END UP WITH AN INCORRECT EVENT, WHICH WOULD HAVE NEVER BEEN FIRED. So whey didn't we fire an event in the domain service or entity ?
     *    Because if we want to fire events in the domain core, we need to persist the business logic results in the domain service or entity. since we want to persist operations before event firing. However, as we mentioned
     *    we prefer to delegate the repository calls to the application service to prevent doing an unnecessary job in the domain core module, which should only focus on business logic. The domain has no knowledge about event
     *    publishing oir event tracking. It only creates and returns the events after running business logic Application service will decide when and how to raise events.
     *    Other approaches, when it comes to the domain events handling process, exit such as firing event in the domain entity or in the domain service. However, we prefer to create and return the domain event from the domain
     *    core module and delegate the event firing process to the application service because of the reasons that we mentioned.
     *    **********************************************************************************************************************************************************************************************************************************
     *    **********************************************************************************************************************************************************************************************************************************
     *  => Where should we create the domain events, in the entity or the domain service. Naturally, domain entities are responsible to create the related events as they can be directly called from the application service because in
     *     Domain-Driven Design, using a domain service is not mandatory. As we mentioned, domain service is required if we have access to multiple aggregates in business logic, or we have some logic that doesn't fit into any entity
     *     class. However, here we also follow a personalized approach and always integrate a domain service ion front of the domain. So my application service will never talk to entities directly. Because of that, I can safely move
     *     the event creation into the domain service.
     */


    /**
     * This method will call the required business methods from entities to initiate and validate an order.
     *
     * @param order      order to create.
     * @param restaurant restaurant entity.
     * @return an order created event.
     */
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    /**
     * Call business methods to pay order.
     *
     * @param order order to pay.
     * @return order paid event.
     */
    OrderPaidEvent payOrder(Order order);

    /**
     * Call business methods to approve order.
     *
     * @param order order to approve.
     */
    void approveOrder(Order order);

    /**
     * Call business methods to cancel an order payment.
     *
     * @param order           order with payment to cancel.
     * @param failureMessages list of messages that explain why an order has failed.
     * @return order cancelled event.
     */
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    /**
     * Call business methods to cancel an order.
     *
     * @param order           order to cancel.
     * @param failureMessages list of messages that explain why an order has failed.
     */
    void cancelOrder(Order order, List<String> failureMessages);
}
