package es.ogboost.banktech.infrastructure.messages;

/**
 * Contains constants for all API endpoint paths.
 *
 * <p>This class provides centralized route definitions to be used in
 * controllers, ensuring consistency and avoiding hard-coded strings.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * &#64;RequestMapping(ApiRoutes.ACCOUNTS)
 * public class AccountController { ... }
 * </pre>
 */
public final class ApiRoutes {

    private ApiRoutes() {
        // Private constructor to prevent instantiation
    }

    /** Base path for all API endpoints */
    public static final String BASE = "/api";

    /** Path for account-related endpoints */
    public static final String ACCOUNTS = BASE + "/accounts";

    /** Path for customer-related endpoints */
    public static final String CUSTOMERS = BASE + "/customers";
}