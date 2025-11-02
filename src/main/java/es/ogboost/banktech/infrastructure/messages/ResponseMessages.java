package es.ogboost.banktech.infrastructure.messages;

/**
 * Centralized success and informational messages for API responses.
 */
public final class ResponseMessages {

    private ResponseMessages() {}

    // 🔹 Account messages
    public static final String ACCOUNT_CREATED = "Account created successfully";
    public static final String ACCOUNT_RETRIEVED = "Account retrieved successfully";
    public static final String ACCOUNT_LIST = "List of all accounts";
    public static final String ACCOUNT_UPDATED = "Account updated successfully";
    public static final String ACCOUNT_DELETED = "Account deleted successfully";

    // 🔹 Customer messages
    public static final String CUSTOMER_CREATED = "Customer created successfully";
    public static final String CUSTOMER_RETRIEVED = "Customer retrieved successfully";
    public static final String CUSTOMER_LIST = "List of all customers";
    public static final String CUSTOMER_UPDATED = "Customer updated successfully";
    public static final String CUSTOMER_DELETED = "Customer deleted successfully";
}