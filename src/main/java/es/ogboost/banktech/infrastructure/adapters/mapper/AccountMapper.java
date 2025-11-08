package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between {@link Account} domain objects
 * and {@link AccountDTO} data transfer objects.
 *
 * <p>Provides methods for both single object mapping and list mapping.</p>
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Converts a domain {@link Account} to a {@link AccountDTO}.
     *
     * @param account the domain account
     * @return the corresponding DTO
     */
    AccountDTO toDto(Account account);

    /**
     * Converts a {@link AccountDTO} to a domain {@link Account}.
     *
     * @param dto the account DTO
     * @return the corresponding domain object
     */
    Account toDomain(AccountDTO dto);

    /**
     * Converts a list of {@link Account} domain objects to a list of {@link AccountDTO}.
     *
     * @param accounts the list of domain accounts
     * @return the list of DTOs
     */
    List<AccountDTO> toDtoList(List<Account> accounts);
}