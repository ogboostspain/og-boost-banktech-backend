package es.ogboost.banktech.infrastructure.messages;

/**
 * Centralized messages for exception handling and API error responses.
 */
public final class ExceptionMessages {

    private ExceptionMessages() {}

    // 🔹 Entity-related
    public static final String ENTITY_NOT_FOUND = "Entity not found";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String ACCOUNT_NOT_FOUND = "Account not found";

    // 🔹 Validation
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String INVALID_REQUEST = "Invalid request";

    // 🔹 Security / Access
    public static final String ACCESS_DENIED = "Access denied";

    // 🔹 Server errors
    public static final String UNEXPECTED_ERROR = "Unexpected server error";
    public static final String UNHANDLED_EXCEPTION = "Unhandled exception";
}