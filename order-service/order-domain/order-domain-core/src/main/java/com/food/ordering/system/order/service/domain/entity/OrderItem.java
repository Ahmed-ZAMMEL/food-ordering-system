package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private final int quantity;
    private final Money money;
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
        money = builder.money;
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

    public Money getMoney() {
        return money;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    /*
    NOTE => Make the builder a static class. Then it will work. If it is non-static, it would require an instance of its owning class - and the point is not to have an instance of it, and even to forbid making instances without the builder.
     */
    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private int quantity;
        private Money money;
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

        public Builder money(Money val) {
            money = val;
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
