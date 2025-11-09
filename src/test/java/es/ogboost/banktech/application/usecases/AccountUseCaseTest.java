package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.AccountRepositoryPort;
import es.ogboost.banktech.domain.exceptions.AccountNotFoundException;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import es.ogboost.banktech.infrastructure.testutils.TestDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountUseCaseTest {

    @Mock
    private AccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private AccountUseCase accountUseCase;

    private AutoCloseable closeable;
    private Account account;
    private Account updatedAccount;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        account = TestDataFactory.baseAccount();
        updatedAccount = TestDataFactory.createAccount(
                1L, "ES7620770024003102575766", "Checking", 1500.0, 1L
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createAccount_ShouldSaveAndReturnAccount() {
        when(accountRepositoryPort.save(account)).thenReturn(account);

        Account result = accountUseCase.createAccount(account);

        assertNotNull(result);
        assertEquals(account, result);
        verify(accountRepositoryPort, times(1)).save(account);
    }

    @Test
    void getAccount_ShouldReturnExistingAccount() {
        when(accountRepositoryPort.findById(1L)).thenReturn(Optional.of(account));

        Account result = accountUseCase.getAccount(1L);

        assertNotNull(result);
        assertEquals(account, result);
        verify(accountRepositoryPort, times(1)).findById(1L);
    }

    @Test
    void getAccount_ShouldThrow_WhenNotFound() {
        when(accountRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> accountUseCase.getAccount(99L));

        assertTrue(ex.getMessage().contains(ExceptionMessages.ACCOUNT_NOT_FOUND));
        verify(accountRepositoryPort, times(1)).findById(99L);
    }

    @Test
    void getAllAccounts_ShouldReturnList() {
        when(accountRepositoryPort.findAll()).thenReturn(List.of(account));

        List<Account> result = accountUseCase.getAllAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(account, result.get(0));
        verify(accountRepositoryPort, times(1)).findAll();
    }

    @Test
    void updateAccount_ShouldModifyExistingAccount() {
        when(accountRepositoryPort.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepositoryPort.save(any(Account.class))).thenReturn(updatedAccount);

        Account result = accountUseCase.updateAccount(1L, updatedAccount);

        assertNotNull(result);
        assertEquals("Checking", result.getAccountType());
        assertEquals(updatedAccount.getBalance(), result.getBalance());
        verify(accountRepositoryPort, times(1)).findById(1L);
        verify(accountRepositoryPort, times(1)).save(any(Account.class));
    }

    @Test
    void updateAccount_ShouldThrow_WhenAccountNotFound() {
        when(accountRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> accountUseCase.updateAccount(1L, updatedAccount));

        assertTrue(ex.getMessage().contains(ExceptionMessages.ACCOUNT_NOT_FOUND));
        verify(accountRepositoryPort, times(1)).findById(1L);
        verify(accountRepositoryPort, never()).save(any());
    }

    @Test
    void deleteAccount_ShouldRemoveAccount() {
        when(accountRepositoryPort.findById(1L)).thenReturn(Optional.of(account));
        doNothing().when(accountRepositoryPort).deleteById(1L);

        accountUseCase.deleteAccount(1L);

        verify(accountRepositoryPort, times(1)).findById(1L);
        verify(accountRepositoryPort, times(1)).deleteById(1L);
    }

    @Test
    void deleteAccount_ShouldThrow_WhenAccountNotFound() {
        when(accountRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> accountUseCase.deleteAccount(99L));

        assertTrue(ex.getMessage().contains(ExceptionMessages.ACCOUNT_NOT_FOUND));
        verify(accountRepositoryPort, times(1)).findById(99L);
        verify(accountRepositoryPort, never()).deleteById(anyLong());
    }
}