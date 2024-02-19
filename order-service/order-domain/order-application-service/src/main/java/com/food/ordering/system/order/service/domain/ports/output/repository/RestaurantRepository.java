package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    // The restaurant entity contains ids of the products, this enables us to get details of products, including the name and the price/
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
