package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/*
 * This  class will be used in "OrderService" and "PaymentService"
 * to work on monetary calculations */

/*
 * => NOTE: one of the advantages of using value object is it brings context to the value.
 * For example, some operations will be delegated to this class, money calculations.
 * */
public class Money {
    /*
     * Object value should be immutable, we are making Money object immutable by making the amount field FINAL!
     * Since this later is final we should set it on the constructor.
     * */

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    /*
     ** Be careful!
     * amount = 0.00 => amount.equals(BigDecimal.ZERO) > 0 => returns FALSE.
     * amount = 0.00 => amount.compareTo(BigDecimal.ZERO) > 0 => returns FALSE.
     * => So by using compareTo method, we will be sure that the comparison return the correct result.
     */
    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    /*
     * => NOTE: if operation includes monetary calculations,
     * we need to be more cautious to care the precision and rounding operations.
     * => NOTE: setScale 2, the number of digits after decimal point is 2, e.g. 10.75 or 500.80
     * */

    /*
     * This way, we can avoid dealing with repeating digits after comma.
     * Example: 1/3 = 0.33333333333333333333, 7/10 = 111/1010 = 0.10110011001100
     * An other elegant manner of doing it is to use Fractional Numbers in Java */

    /*
     * => NOTE: RoundingMode.HALF_EVEN, rounds toward to nearest neighbor.
     * If both neighbors are equidistant, round towards the even neighbor. */

    /**
     * set the scale (number of digits after comma) of a BigDecimal.
     *
     * @param input amount, price, ...
     * @return input with two digits after comma.
     */
    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}