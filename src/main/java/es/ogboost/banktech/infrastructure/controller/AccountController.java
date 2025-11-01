package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.AccountUseCase;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.controller.response.ApiResponse;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
import es.ogboost.banktech.infrastructure.adapters.mapper.AccountMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountUseCase useCase;
    private final AccountMapper mapper;

    public AccountController(AccountUseCase useCase, AccountMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDTO>> create(@RequestBody AccountDTO dto) {
        Account created = useCase.createAccount(mapper.toDomain(dto));
        return ResponseEntity.ok(ApiResponse.ok("Account created successfully", mapper.toDto(created)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getById(@PathVariable Long id) {
        Account found = useCase.getAccount(id);
        return ResponseEntity.ok(ApiResponse.ok("Account retrieved successfully", mapper.toDto(found)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDTO>>> getAll() {
        List<Account> accounts = useCase.getAllAccounts();
        return ResponseEntity.ok(ApiResponse.ok("List of all accounts", mapper.toDtoList(accounts)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> update(@PathVariable Long id, @RequestBody AccountDTO dto) {
        Account updated = useCase.updateAccount(id, mapper.toDomain(dto));
        return ResponseEntity.ok(ApiResponse.ok("Account updated successfully", mapper.toDto(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        useCase.deleteAccount(id);
        return ResponseEntity.ok(ApiResponse.ok("Account deleted successfully", null));
    }
}