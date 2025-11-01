package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.AccountRepositoryPort;
import es.ogboost.banktech.domain.model.Account;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public AccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public Account createAccount(Account account) {
        return accountRepositoryPort.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepositoryPort.findAll();
    }

    public Account updateAccount(Long id, Account updated) {
        Account existing = getAccount(id);
        existing.setBalance(updated.getBalance());
        existing.setAccountType(updated.getAccountType());
        return accountRepositoryPort.save(existing);
    }

    public void deleteAccount(Long id) {
        accountRepositoryPort.deleteById(id);
    }
}