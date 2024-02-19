package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId productId, String name, Money price) {
        /*
         * We should use the constructor approach, when ywe want to create a new instance of the object, with the values already populated(a ready to use object with value populated).
         * This way, we need not explicitly call the setter methods for each field in the object to populate them.
         * We set the value using a setter approach, when we want to change the value of a field, after the object has been created.
         * **************************************************************************************************************************
         * => NOTE: since a value object is immutable, we are obliged to use constructor to set fields, such as ID.
         ********** But for entity we can use a setter. It is the case of the ProductId.
         * */
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public Product(ProductId productId) {
        super.setId(productId);
    }

    /**
     * Update product information with information got from a restaurant.
     *
     * @param name  product name.
     * @param price product price.
     */
    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    /*
     * We don't need to override HashCode and Equals methods because they are already overwritten in the base class, and
     * it only compares the identifier of the entity.
     * REMINDER: for entities we should have a unique identifier, so that's why we are using this unique ID in the Equals
     * and HashCode methods.
     */
}
