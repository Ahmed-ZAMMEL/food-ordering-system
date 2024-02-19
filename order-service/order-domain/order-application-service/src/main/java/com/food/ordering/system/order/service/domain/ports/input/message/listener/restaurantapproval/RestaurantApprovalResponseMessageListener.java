package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    /**
     * @param restaurantApprovalResponse
     */
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    /**
     * @param restaurantApprovalResponse
     */
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
