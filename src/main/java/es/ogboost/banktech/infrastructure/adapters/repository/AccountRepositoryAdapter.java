package es.ogboost.banktech.infrastructure.adapters.repository;

import es.ogboost.banktech.application.ports.AccountRepositoryPort;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.mapper.AccountMapper;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.JpaAccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final JpaAccountRepository repository;
    private final AccountMapper mapper;

    public AccountRepositoryAdapter(JpaAccountRepository repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Account save(Account account) {
        return mapper.toDomain(repository.save(mapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}