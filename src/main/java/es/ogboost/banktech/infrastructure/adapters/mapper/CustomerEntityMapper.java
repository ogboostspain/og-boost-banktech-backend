package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);
    Customer toDomain(CustomerEntity entity);
    List<Customer> toDomainList(List<CustomerEntity> entities);
}