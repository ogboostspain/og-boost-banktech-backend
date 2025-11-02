package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.CustomerRepositoryPort;
import es.ogboost.banktech.domain.exceptions.CustomerNotFoundException;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public Customer createCustomer(Customer customer) {
        log.info("Creating new customer: {}", customer.getEmail());
        return customerRepositoryPort.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepositoryPort.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        ExceptionMessages.CUSTOMER_NOT_FOUND + " id=" + id));
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepositoryPort.findAll();
        log.debug("Fetched {} customers from repository", customers.size());
        return customers;
    }

    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = getCustomer(id);

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setDni(updated.getDni());
        existing.setEmail(updated.getEmail());

        log.info("Updating customer {} (id={})", existing.getEmail(), existing.getId());
        return customerRepositoryPort.save(existing);
    }

    public void deleteCustomer(Long id) {
        Customer existing = getCustomer(id);
        log.warn("Deleting customer: {} (id={})", existing.getEmail(), id);
        customerRepositoryPort.deleteById(id);
    }
}