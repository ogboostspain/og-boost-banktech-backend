package es.ogboost.banktech.infrastructure.adapters.repository.jpa;

import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> { }