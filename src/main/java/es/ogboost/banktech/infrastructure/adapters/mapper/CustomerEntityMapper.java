package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between {@link Customer} domain objects
 * and {@link CustomerEntity} JPA entities.
 *
 * <p>Provides methods for both single object mapping and list mapping.</p>
 */
@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    /**
     * Converts a domain {@link Customer} to a JPA {@link CustomerEntity}.
     *
     * @param customer the domain customer
     * @return the corresponding entity
     */
    CustomerEntity toEntity(Customer customer);

    /**
     * Converts a JPA {@link CustomerEntity} to a domain {@link Customer}.
     *
     * @param entity the customer entity
     * @return the corresponding domain object
     */
    Customer toDomain(CustomerEntity entity);

    /**
     * Converts a list of {@link CustomerEntity} objects to a list of {@link Customer} domain objects.
     *
     * @param entities the list of customer entities
     * @return the list of domain customers
     */
    List<Customer> toDomainList(List<CustomerEntity> entities);
}