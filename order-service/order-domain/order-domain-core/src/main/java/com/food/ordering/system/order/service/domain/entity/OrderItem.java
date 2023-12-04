package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;
    private OrderId orderId;

    /*
     * => TIPS: we will create a Builder using the IntelliJ InnerBuilder plugin (since we can't use lombok,
     * it will create dependency inside the domain-core module!), so that it'll be easier to create
     * the object fluently instead of using a constructor with many parameters.
     *
     * */

    // The construct is private we need to use builder to create this object.
    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        orderId = builder.orderId;
    }

    //Getters will be required for mappers.

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }


    /*
     *Actually, we can make this initialize order item method package private in the order item class,
     * because we will not reach it outside the package. It should only be called from order entity, so we remove
     * the public access.
     */

    /**
     * Initialize order and orderItem ids.
     *
     * @param orderId
     * @param orderItemId
     */
    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    /**
     * Check if the price is valid.
     *
     * @return ture if the price is valid, false otherwise.
     */
    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }


    /*
     * NOTE => Make the builder a static class. Then it will work. If it is non-static, it would require an instance of its owning class - and the point is not to have an instance of it, and even to forbid making instances without the builder.
     */
    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;
        private OrderId orderId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
