package es.ogboost.banktech.application.ports;

import es.ogboost.banktech.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findById(Long id);
    List<Account> findAll();
    void deleteById(Long id);
}