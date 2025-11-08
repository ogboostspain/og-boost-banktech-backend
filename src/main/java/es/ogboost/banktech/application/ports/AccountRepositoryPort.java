package es.ogboost.banktech.application.ports;

import es.ogboost.banktech.domain.model.Account;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for account persistence operations.
 *
 * <p>Defines the contract for saving, retrieving, listing, and deleting
 * {@link Account} domain objects. Implementations may use JPA, JDBC, or
 * any other persistence mechanism.</p>
 */
public interface AccountRepositoryPort {

    /**
     * Saves a new account or updates an existing one.
     *
     * @param account the account to save
     * @return the saved account
     */
    Account save(Account account);

    /**
     * Finds an account by its unique identifier.
     *
     * @param id the account ID
     * @return an {@link Optional} containing the account if found, or empty otherwise
     */
    Optional<Account> findById(Long id);

    /**
     * Retrieves all accounts.
     *
     * @return a list of all accounts
     */
    List<Account> findAll();

    /**
     * Deletes an account by its unique identifier.
     *
     * @param id the account ID to delete
     */
    void deleteById(Long id);
}