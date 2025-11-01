package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDto(Account account);
    Account toDomain(AccountDTO dto);
    List<AccountDTO> toDtoList(List<Account> accounts);
}