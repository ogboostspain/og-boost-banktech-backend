package es.ogboost.banktech.application.ports;

import es.ogboost.banktech.domain.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for customer persistence operations.
 *
 * <p>Defines the contract for saving, retrieving, listing, and deleting
 * {@link Customer} domain objects. Implementations may use JPA, JDBC, or
 * any other persistence mechanism.</p>
 */
public interface CustomerRepositoryPort {

    /**
     * Saves a new customer or updates an existing one.
     *
     * @param customer the customer to save
     * @return the saved customer
     */
    Customer save(Customer customer);

    /**
     * Finds a customer by its unique identifier.
     *
     * @param id the customer ID
     * @return an {@link Optional} containing the customer if found, or empty otherwise
     */
    Optional<Customer> findById(Long id);

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    List<Customer> findAll();

    /**
     * Deletes a customer by its unique identifier.
     *
     * @param id the customer ID to delete
     */
    void deleteById(Long id);
}