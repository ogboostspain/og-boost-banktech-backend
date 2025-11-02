package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.AccountRepositoryPort;
import es.ogboost.banktech.domain.exceptions.AccountNotFoundException;
import es.ogboost.banktech.domain.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class AccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public AccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public Account createAccount(Account account) {
        validateAccount(account);
        log.info("Creating new account of type {} with balance {}", account.getAccountType(), account.getBalance());
        return accountRepositoryPort.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepositoryPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepositoryPort.findAll();
        log.debug("Retrieved {} accounts from repository", accounts.size());
        return accounts;
    }

    public Account updateAccount(Long id, Account updated) {
        Account existing = getAccount(id);
        validateAccount(updated);

        existing.setAccountType(updated.getAccountType());
        existing.setBalance(updated.getBalance());

        log.info("Updating account (id={}) to new balance: {}", id, existing.getBalance());
        return accountRepositoryPort.save(existing);
    }

    public void deleteAccount(Long id) {
        Account existing = getAccount(id);
        log.warn("Deleting account with id={} and type={}", id, existing.getAccountType());
        accountRepositoryPort.deleteById(id);
    }

    private void validateAccount(Account account) {
        if (account.getBalance() == null || account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Account balance cannot be null or negative");
        }
        if (account.getAccountType() == null || account.getAccountType().isBlank()) {
            throw new IllegalArgumentException("Account type cannot be empty");
        }
    }
}