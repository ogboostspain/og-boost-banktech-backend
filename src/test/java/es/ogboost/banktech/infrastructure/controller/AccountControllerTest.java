package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.AccountUseCase;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.mapper.AccountMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
import es.ogboost.banktech.infrastructure.messages.ResponseMessages;
import es.ogboost.banktech.infrastructure.testutils.TestDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountUseCase accountUseCase;

    @Mock
    private AccountMapper accountMapper;

    private AutoCloseable closeable;

    private Account account;
    private AccountDTO accountDTO;
    private Account updatedAccount;
    private AccountDTO updatedAccountDTO;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        account = TestDataFactory.baseAccount();
        accountDTO = TestDataFactory.baseAccountDTO();

        updatedAccount = TestDataFactory.createAccount(
                1L, "Checking", 1500.00, 1L
        );
        updatedAccountDTO = TestDataFactory.createAccountDTO(
                1L, "Checking", 1500.00, 1L
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void create_ShouldReturnCreatedAccount() {
        when(accountMapper.toDomain(accountDTO)).thenReturn(account);
        when(accountUseCase.createAccount(account)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDTO);

        ResponseEntity<ApiResult<AccountDTO>> response = accountController.create(accountDTO);
        ApiResult<AccountDTO> body = response.getBody();
        assertNotNull(body);

        assertEquals(ResponseMessages.ACCOUNT_CREATED, body.getMessage());
        assertTrue(body.isSuccess());
        assertEquals(accountDTO, body.getData());

        verify(accountUseCase, times(1)).createAccount(account);
        verify(accountMapper, times(1)).toDomain(accountDTO);
        verify(accountMapper, times(1)).toDto(account);
    }

    @Test
    void getById_ShouldReturnAccount() {
        when(accountUseCase.getAccount(1L)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDTO);

        ResponseEntity<ApiResult<AccountDTO>> response = accountController.getById(1L);
        ApiResult<AccountDTO> body = response.getBody();
        assertNotNull(body);

        assertEquals(ResponseMessages.ACCOUNT_RETRIEVED, body.getMessage());
        assertEquals(accountDTO, body.getData());

        verify(accountUseCase, times(1)).getAccount(1L);
        verify(accountMapper, times(1)).toDto(account);
    }

    @Test
    void getAll_ShouldReturnListOfAccounts() {
        List<Account> accounts = List.of(account);
        List<AccountDTO> dtos = List.of(accountDTO);

        when(accountUseCase.getAllAccounts()).thenReturn(accounts);
        when(accountMapper.toDtoList(accounts)).thenReturn(dtos);

        ResponseEntity<ApiResult<List<AccountDTO>>> response = accountController.getAll();
        ApiResult<List<AccountDTO>> body = response.getBody();
        assertNotNull(body);

        assertEquals(ResponseMessages.ACCOUNT_LIST, body.getMessage());
        assertEquals(dtos, body.getData());

        verify(accountUseCase, times(1)).getAllAccounts();
        verify(accountMapper, times(1)).toDtoList(accounts);
    }

    @Test
    void update_ShouldReturnUpdatedAccount() {
        when(accountMapper.toDomain(updatedAccountDTO)).thenReturn(updatedAccount);
        when(accountUseCase.updateAccount(1L, updatedAccount)).thenReturn(updatedAccount);
        when(accountMapper.toDto(updatedAccount)).thenReturn(updatedAccountDTO);

        ResponseEntity<ApiResult<AccountDTO>> response = accountController.update(1L, updatedAccountDTO);
        ApiResult<AccountDTO> body = response.getBody();
        assertNotNull(body);

        assertEquals(ResponseMessages.ACCOUNT_UPDATED, body.getMessage());
        assertEquals(updatedAccountDTO, body.getData());

        verify(accountMapper, times(1)).toDomain(updatedAccountDTO);
        verify(accountUseCase, times(1)).updateAccount(1L, updatedAccount);
        verify(accountMapper, times(1)).toDto(updatedAccount);
    }

    @Test
    void delete_ShouldReturnVoidResponse() {
        doNothing().when(accountUseCase).deleteAccount(1L);

        ResponseEntity<ApiResult<Void>> response = accountController.delete(1L);
        ApiResult<Void> body = response.getBody();
        assertNotNull(body);

        assertEquals(ResponseMessages.ACCOUNT_DELETED, body.getMessage());
        assertNull(body.getData());

        verify(accountUseCase, times(1)).deleteAccount(1L);
    }

    // ==========================
    // Tests de errores
    // ==========================

    @Test
    void getById_ShouldReturnNotFound() {
        when(accountUseCase.getAccount(1L)).thenThrow(new RuntimeException("Account not found"));

        Exception exception = assertThrows(RuntimeException.class, () -> accountController.getById(1L));
        assertEquals("Account not found", exception.getMessage());

        verify(accountUseCase, times(1)).getAccount(1L);
    }

    @Test
    void update_ShouldReturnNotFound() {
        when(accountMapper.toDomain(updatedAccountDTO)).thenReturn(updatedAccount);
        when(accountUseCase.updateAccount(1L, updatedAccount))
                .thenThrow(new RuntimeException("Account not found"));

        Exception exception = assertThrows(RuntimeException.class,
                () -> accountController.update(1L, updatedAccountDTO));
        assertEquals("Account not found", exception.getMessage());

        verify(accountMapper, times(1)).toDomain(updatedAccountDTO);
        verify(accountUseCase, times(1)).updateAccount(1L, updatedAccount);
    }

    @Test
    void delete_ShouldReturnNotFound() {
        doThrow(new RuntimeException("Account not found")).when(accountUseCase).deleteAccount(1L);

        Exception exception = assertThrows(RuntimeException.class,
                () -> accountController.delete(1L));
        assertEquals("Account not found", exception.getMessage());

        verify(accountUseCase, times(1)).deleteAccount(1L);
    }
}