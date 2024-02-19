package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

/**
 * Remember, We have mentioned that domain event listeners are a special type of application services, and they are triggered by domain events not by clients.
 * As we will see, when we implement the payment in a restaurant services, we will raise domain events on those services, and it'll trigger the message listeners here in the order service.
 */
public interface PaymentResponseMessageListener {

    /**
     * Listen to a kafka topic for payment.
     *
     * @param paymentResponse payment response.
     */
    void paymentCompleted(PaymentResponse paymentResponse);

    /**
     * Listen to a kafka topic for payment.
     * This method can be called in case a payment is failed because of a business logic invariant.
     * However, it can be a response to the payment cancel request as part of the saga rollback operation.
     *
     * @param paymentResponse payment response.
     */
    void paymentCancelled(PaymentResponse paymentResponse);
}
