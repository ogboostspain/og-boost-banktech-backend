package es.ogboost.banktech.domain.exceptions;

/**
 * Exception thrown when an account with a specified ID is not found.
 *
 * <p>Extends {@link RuntimeException} so it can be used as an unchecked exception
 * throughout the application.</p>
 */
public class AccountNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code AccountNotFoundException} with the specified detail message.
     *
     * @param message the detail message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}