package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDto(Customer customer);
    Customer toDomain(CustomerDTO dto);
    List<CustomerDTO> toDtoList(List<Customer> customers);
}