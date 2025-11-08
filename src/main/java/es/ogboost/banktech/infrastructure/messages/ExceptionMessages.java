package es.ogboost.banktech.infrastructure.messages;

/**
 * Centralized messages for exception handling and API error responses.
 *
 * <p>This class contains all standard error messages used across the application,
 * ensuring consistency and avoiding hard-coded strings in controllers and services.</p>
 */
public final class ExceptionMessages {

    private ExceptionMessages() {}

    /** Generic entity not found message */
    public static final String ENTITY_NOT_FOUND = "Entity not found";

    /** Message for customer not found */
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";

    /** Message for account not found */
    public static final String ACCOUNT_NOT_FOUND = "Account not found";

    /** Message used when validation fails */
    public static final String VALIDATION_FAILED = "Validation failed";

    /** Message used for invalid requests */
    public static final String INVALID_REQUEST = "Invalid request";

    /** Message used when access is denied */
    public static final String ACCESS_DENIED = "Access denied";

    /** Message for unexpected server errors */
    public static final String UNEXPECTED_ERROR = "Unexpected server error";

    /** Message for unhandled exceptions */
    public static final String UNHANDLED_EXCEPTION = "Unhandled exception";
}