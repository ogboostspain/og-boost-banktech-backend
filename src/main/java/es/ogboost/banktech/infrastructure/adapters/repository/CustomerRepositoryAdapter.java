package es.ogboost.banktech.infrastructure.adapters.repository;

import es.ogboost.banktech.application.ports.CustomerRepositoryPort;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.mapper.CustomerMapper;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.JpaCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerRepositoryAdapter(JpaCustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        return mapper.toDomain(repository.save(mapper.toEntity(customer)));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
