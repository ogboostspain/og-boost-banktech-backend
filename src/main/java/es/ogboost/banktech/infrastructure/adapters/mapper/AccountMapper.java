package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity toEntity(Account domain);
    Account toDomain(AccountEntity entity);
}