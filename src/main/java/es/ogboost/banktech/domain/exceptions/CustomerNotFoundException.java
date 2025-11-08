package es.ogboost.banktech.domain.exceptions;

/**
 * Exception thrown when a customer with a specified ID is not found.
 *
 * <p>Extends {@link RuntimeException} so it can be used as an unchecked exception
 * throughout the application.</p>
 */
public class CustomerNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code CustomerNotFoundException} with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}