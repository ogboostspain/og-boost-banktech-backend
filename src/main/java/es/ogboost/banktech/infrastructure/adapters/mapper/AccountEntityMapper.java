package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {
    AccountEntity toEntity(Account account);
    Account toDomain(AccountEntity entity);
    List<Account> toDomainList(List<AccountEntity> entities);
}