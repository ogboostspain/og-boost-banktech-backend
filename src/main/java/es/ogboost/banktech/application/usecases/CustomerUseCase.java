package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.CustomerRepositoryPort;
import es.ogboost.banktech.domain.model.Customer;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepositoryPort.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }

    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = getCustomer(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setDni(updated.getDni());
        existing.setEmail(updated.getEmail());
        return customerRepositoryPort.save(existing);
    }

    public void deleteCustomer(Long id) {
        customerRepositoryPort.deleteById(id);
    }
}