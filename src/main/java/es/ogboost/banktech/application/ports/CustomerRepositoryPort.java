package es.ogboost.banktech.application.ports;

import es.ogboost.banktech.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
}