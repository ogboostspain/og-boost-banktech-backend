package es.ogboost.banktech.infrastructure.messages;

public final class ValidationMessages {

    private ValidationMessages() {
        // Prevent instantiation
    }

    // 🔹 Account validation
    public static final String ACCOUNT_TYPE_REQUIRED = "Account type is required";
    public static final String BALANCE_REQUIRED = "Balance is required";
    public static final String BALANCE_POSITIVE = "Balance must be greater than zero";

    // 🔹 Customer validation
    public static final String FIRST_NAME_REQUIRED = "First name is required";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID = "Invalid email format";
    public static final String DNI_REQUIRED = "DNI is required";

    // 🔹 Generic error messages
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
}