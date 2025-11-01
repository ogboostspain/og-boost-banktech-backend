package es.ogboost.banktech.infrastructure.adapters.repository.jpa;

import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> { }