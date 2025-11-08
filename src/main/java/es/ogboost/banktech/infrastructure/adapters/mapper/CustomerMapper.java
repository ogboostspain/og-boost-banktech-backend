package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between {@link Customer} domain objects
 * and {@link CustomerDTO} data transfer objects.
 *
 * <p>Provides methods for both single object mapping and list mapping.</p>
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * Converts a domain {@link Customer} to a {@link CustomerDTO}.
     *
     * @param customer the domain customer
     * @return the corresponding DTO
     */
    CustomerDTO toDto(Customer customer);

    /**
     * Converts a {@link CustomerDTO} to a domain {@link Customer}.
     *
     * @param dto the customer DTO
     * @return the corresponding domain object
     */
    Customer toDomain(CustomerDTO dto);

    /**
     * Converts a list of {@link Customer} domain objects to a list of {@link CustomerDTO}.
     *
     * @param customers the list of domain customers
     * @return the list of DTOs
     */
    List<CustomerDTO> toDtoList(List<Customer> customers);
}