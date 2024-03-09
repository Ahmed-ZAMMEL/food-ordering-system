package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class will be a spring component so that we can inject and use it from the service classes.
 */
@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                /*
                 * As we see, we only set the id of the product here as we don't have the product price information.
                 * That, we will get from the restaurant repository. For this, we will create a new constructor in Product
                 * entity, only with the id property com.food.ordering.system.order.service.domain.entity.Product.Product(com.food.ordering.system.domain.valueobject.ProductId).
                 *
                 * */
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Convert a createOrderCommand object to an order object.
     * NOTE:
     * => We want to create an order object from the create order command. To do that, we will add another method in the data clas,
     * create order commands to order, and then create an order object in these methods. As we see, we are using this order data
     * mapper class to create the domain objects from the input data transfer object and vice versa. In the domain-driven terms,
     * we may see this as the Factory because we delegate the object conversion and creation operations to this mapper class.
     * => We don't set the order id, tracking id and order status. Those are the fields that we will set according to business
     * rules on the domain service. For now, we only mapped the input values that we get from the clients and create an order
     * object.
     *
     * @param createOrderCommand order.
     * @return order.
     */
    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    /**
     * Convert an order address to a street address.
     *
     * @param orderAddress order address
     * @return street address.
     */
    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.Builder
                                .builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    /**
     * Convert an order to create order response.
     *
     * @param order order
     * @return create order response
     */
    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTranTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
