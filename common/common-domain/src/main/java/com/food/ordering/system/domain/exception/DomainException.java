package com.food.ordering.system.domain.exception;

/**
 * The OrderDomainException wraps all checked standard Java exception and enriches them with a custom error message.
 * This exception will be the basic class for all the business logic exceptions, those which occur in the domain-core layer.
 * It can be seen as a marker class.
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
