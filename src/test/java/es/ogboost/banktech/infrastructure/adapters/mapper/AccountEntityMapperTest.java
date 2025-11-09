package es.ogboost.banktech.infrastructure.adapters.mapper;

import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
import es.ogboost.banktech.infrastructure.testutils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntityMapperTest {

    private final AccountEntityMapper mapper = Mappers.getMapper(AccountEntityMapper.class);

    @Test
    void toEntity_ShouldMapAllFields() {
        Account account = TestDataFactory.baseAccount();

        AccountEntity entity = mapper.toEntity(account);

        assertNotNull(entity);
        assertEquals(account.getId(), entity.getId());
        assertEquals(account.getAccountType(), entity.getAccountType());
        assertEquals(account.getAccountNumber(), entity.getAccountNumber());
        assertEquals(account.getBalance(), entity.getBalance());
        assertEquals(account.getCustomerId(), entity.getCustomerId());
    }

    @Test
    void toDomain_ShouldMapAllFields() {
        AccountEntity entity = TestDataFactory.baseAccountEntity();

        Account account = mapper.toDomain(entity);

        assertNotNull(account);
        assertEquals(entity.getId(), account.getId());
        assertEquals(entity.getAccountType(), account.getAccountType());
        assertEquals(entity.getAccountNumber(), account.getAccountNumber());
        assertEquals(entity.getBalance(), account.getBalance());
        assertEquals(entity.getCustomerId(), account.getCustomerId());
    }

    @Test
    void toDomainList_ShouldMapListCorrectly() {
        List<AccountEntity> entities = List.of(
                TestDataFactory.baseAccountEntity(),
                TestDataFactory.createAccountEntity(2L, "CHECKING", "ES222222", 200.00, 2L)
        );

        List<Account> accounts = mapper.toDomainList(entities);

        assertNotNull(accounts);
        assertEquals(2, accounts.size());
        assertEquals("SAVINGS", accounts.get(0).getAccountType());
        assertEquals("ES7620770024003102575766", accounts.get(0).getAccountNumber());
        assertEquals("CHECKING", accounts.get(1).getAccountType());
        assertEquals("ES222222", accounts.get(1).getAccountNumber());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenInputIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toDomain_ShouldReturnNull_WhenInputIsNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void toDomainList_ShouldReturnEmptyList_WhenInputIsEmpty() {
        List<Account> result = mapper.toDomainList(List.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}