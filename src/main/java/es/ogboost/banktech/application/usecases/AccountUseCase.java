package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.AccountRepositoryPort;
import es.ogboost.banktech.domain.exceptions.AccountNotFoundException;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Use case service for managing accounts.
 *
 * <p>Provides operations for creating, retrieving, updating, and deleting
 * {@link Account} entities, enforcing business rules and handling exceptions.</p>
 */
@Slf4j
@Service
public class AccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    /**
     * Constructs the AccountUseCase with the required repository port.
     *
     * @param accountRepositoryPort the repository port for account persistence
     */
    public AccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    /**
     * Creates a new account.
     *
     * @param account the account to create
     * @return the created account
     */
    public Account createAccount(Account account) {
        log.info("Creating new account of type {} with balance {}", account.getAccountType(), account.getBalance());
        return accountRepositoryPort.save(account);
    }

    /**
     * Retrieves an account by its unique ID.
     *
     * @param id the account ID
     * @return the found account
     * @throws AccountNotFoundException if no account is found with the given ID
     */
    public Account getAccount(Long id) {
        return accountRepositoryPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(
                        ExceptionMessages.ACCOUNT_NOT_FOUND + " id=" + id));
    }

    /**
     * Retrieves all accounts.
     *
     * @return a list of all accounts
     */
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepositoryPort.findAll();
        log.debug("Retrieved {} accounts from repository", accounts.size());
        return accounts;
    }

    /**
     * Updates an existing account with new information.
     *
     * @param id      the ID of the account to update
     * @param updated the updated account information
     * @return the updated account
     * @throws AccountNotFoundException if no account exists with the given ID
     */
    public Account updateAccount(Long id, Account updated) {
        Account existing = getAccount(id);

        existing.setAccountType(updated.getAccountType());
        existing.setBalance(updated.getBalance());

        log.info("Updating account (id={}) to new balance: {}", id, existing.getBalance());
        return accountRepositoryPort.save(existing);
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id the ID of the account to delete
     * @throws AccountNotFoundException if no account exists with the given ID
     */
    public void deleteAccount(Long id) {
        Account existing = getAccount(id);
        log.warn("Deleting account with id={} and type={}", id, existing.getAccountType());
        accountRepositoryPort.deleteById(id);
    }
}