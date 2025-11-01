package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity(Customer domain);
    Customer toDomain(CustomerEntity entity);
}