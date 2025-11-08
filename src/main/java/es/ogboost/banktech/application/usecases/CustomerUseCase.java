package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.CustomerRepositoryPort;
import es.ogboost.banktech.domain.exceptions.CustomerNotFoundException;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Use case service for managing customers.
 *
 * <p>Provides operations for creating, retrieving, updating, and deleting
 * {@link Customer} entities, enforcing business rules and handling exceptions.</p>
 */
@Slf4j
@Service
public class CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    /**
     * Constructs the CustomerUseCase with the required repository port.
     *
     * @param customerRepositoryPort the repository port for customer persistence
     */
    public CustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    public Customer createCustomer(Customer customer) {
        log.info("Creating new customer: {}", customer.getEmail());
        return customerRepositoryPort.save(customer);
    }

    /**
     * Retrieves a customer by its unique ID.
     *
     * @param id the customer ID
     * @return the found customer
     * @throws CustomerNotFoundException if no customer is found with the given ID
     */
    public Customer getCustomer(Long id) {
        return customerRepositoryPort.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        ExceptionMessages.CUSTOMER_NOT_FOUND + " id=" + id));
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepositoryPort.findAll();
        log.debug("Fetched {} customers from repository", customers.size());
        return customers;
    }

    /**
     * Updates an existing customer with new information.
     *
     * @param id      the ID of the customer to update
     * @param updated the updated customer information
     * @return the updated customer
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public Customer updateCustomer(Long id, Customer updated) {
        Customer existing = getCustomer(id);

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setDni(updated.getDni());
        existing.setEmail(updated.getEmail());

        log.info("Updating customer {} (id={})", existing.getEmail(), existing.getId());
        return customerRepositoryPort.save(existing);
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id the ID of the customer to delete
     * @throws CustomerNotFoundException if no customer exists with the given ID
     */
    public void deleteCustomer(Long id) {
        Customer existing = getCustomer(id);
        log.warn("Deleting customer: {} (id={})", existing.getEmail(), id);
        customerRepositoryPort.deleteById(id);
    }
}