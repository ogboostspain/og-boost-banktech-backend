package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between {@link Account} domain objects
 * and {@link AccountEntity} JPA entities.
 *
 * <p>Provides methods for both single object mapping and list mapping.</p>
 */
@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

    /**
     * Converts a domain {@link Account} to a JPA {@link AccountEntity}.
     *
     * @param account the domain account
     * @return the corresponding entity
     */
    AccountEntity toEntity(Account account);

    /**
     * Converts a JPA {@link AccountEntity} to a domain {@link Account}.
     *
     * @param entity the account entity
     * @return the corresponding domain object
     */
    Account toDomain(AccountEntity entity);

    /**
     * Converts a list of {@link AccountEntity} objects to a list of {@link Account} domain objects.
     *
     * @param entities the list of account entities
     * @return the list of domain accounts
     */
    List<Account> toDomainList(List<AccountEntity> entities);
}