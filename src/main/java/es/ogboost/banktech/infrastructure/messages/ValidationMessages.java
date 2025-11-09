package es.ogboost.banktech.infrastructure.messages;

/**
 * Centralized validation messages for API request data.
 *
 * <p>This class provides standard messages for field validation errors and
 * generic API errors, ensuring consistency across the application.</p>
 */
public final class ValidationMessages {

    private ValidationMessages() {}

    /** Account type is required */
    public static final String ACCOUNT_TYPE_REQUIRED = "Account type is required";

    /** Account number is required */
    public static final String ACCOUNT_NUMBER_REQUIRED = "Account number is required";

    /** Balance is required */
    public static final String BALANCE_REQUIRED = "Balance is required";

    /** Balance must be greater than zero */
    public static final String BALANCE_POSITIVE = "Balance must be greater than zero";

    /** First name is required */
    public static final String FIRST_NAME_REQUIRED = "First name is required";

    /** Last name is required */
    public static final String LAST_NAME_REQUIRED = "Last name is required";

    /** Email is required */
    public static final String EMAIL_REQUIRED = "Email is required";

    /** Invalid email format */
    public static final String EMAIL_INVALID = "Invalid email format";

    /** DNI is required */
    public static final String DNI_REQUIRED = "DNI is required";

    /** Validation failed */
    public static final String VALIDATION_FAILED = "Validation failed";

    /** Internal server error */
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";

    /** Resource not found */
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
}