package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * This class will be a spring component so that we can inject and use it from the service classes.
 */
@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToResturant(CreateOrderCommand createOrderCommand) {
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
     *
     * @param createOrderCommand order.
     * @return order.
     */
    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        Order.Builder.builder()
                .customerId()
                .restaurantId()
                .deliveryAddress()
                .build();
    }
}
