package com.food.ordering.system.domain.event;

/**
 * Functionally we will not use this generic type, it will help to mark an event object
 * with the type of the entity that will fire that event.
 * For example, when we create a class "OrderCreatedEvent", we will set the generic type
 * as "Order", which is the entity that this event is originated from.
 *
 * @param <T> a generic type used as a marker.
 */
public interface DomainEvent<T> {
}
