package es.ogboost.banktech.infrastructure.messages;

/**
 * Centralized success and informational messages for API responses.
 *
 * <p>This class provides standard messages for successful operations on accounts
 * and customers, ensuring consistency across all API responses.</p>
 */
public final class ResponseMessages {

    private ResponseMessages() {}

    /** Message for successfully created account */
    public static final String ACCOUNT_CREATED = "Account created successfully";

    /** Message for successfully retrieved account */
    public static final String ACCOUNT_RETRIEVED = "Account retrieved successfully";

    /** Message for listing all accounts */
    public static final String ACCOUNT_LIST = "List of all accounts";

    /** Message for successfully updated account */
    public static final String ACCOUNT_UPDATED = "Account updated successfully";

    /** Message for successfully deleted account */
    public static final String ACCOUNT_DELETED = "Account deleted successfully";

    /** Message for successfully created customer */
    public static final String CUSTOMER_CREATED = "Customer created successfully";

    /** Message for successfully retrieved customer */
    public static final String CUSTOMER_RETRIEVED = "Customer retrieved successfully";

    /** Message for listing all customers */
    public static final String CUSTOMER_LIST = "List of all customers";

    /** Message for successfully updated customer */
    public static final String CUSTOMER_UPDATED = "Customer updated successfully";

    /** Message for successfully deleted customer */
    public static final String CUSTOMER_DELETED = "Customer deleted successfully";
}