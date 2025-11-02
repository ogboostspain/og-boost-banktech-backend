package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.AccountUseCase;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.mapper.AccountMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResponse;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
import es.ogboost.banktech.infrastructure.messages.ApiRoutes;
import es.ogboost.banktech.infrastructure.messages.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing accounts.
 */
@RestController
@RequestMapping(ApiRoutes.ACCOUNTS)
public class AccountController extends BaseController {

    private final AccountUseCase useCase;
    private final AccountMapper mapper;

    public AccountController(AccountUseCase useCase, AccountMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDTO>> create(@Valid @RequestBody AccountDTO dto) {
        Account created = useCase.createAccount(mapper.toDomain(dto));
        return respond(ResponseMessages.ACCOUNT_CREATED, mapper.toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getById(@PathVariable Long id) {
        return respond(ResponseMessages.ACCOUNT_RETRIEVED, mapper.toDto(useCase.getAccount(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDTO>>> getAll() {
        return respond(ResponseMessages.ACCOUNT_LIST, mapper.toDtoList(useCase.getAllAccounts()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> update(@PathVariable Long id, @Valid @RequestBody AccountDTO dto) {
        return respond(ResponseMessages.ACCOUNT_UPDATED, mapper.toDto(useCase.updateAccount(id, mapper.toDomain(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        useCase.deleteAccount(id);
        return respond(ResponseMessages.ACCOUNT_DELETED, null);
    }
}