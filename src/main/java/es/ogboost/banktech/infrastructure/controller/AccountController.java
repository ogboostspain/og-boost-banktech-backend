package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.AccountUseCase;
import es.ogboost.banktech.domain.model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountUseCase useCase;

    public AccountController(AccountUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return useCase.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return useCase.getAccount(id);
    }

    @GetMapping
    public List<Account> getAll() {
        return useCase.getAllAccounts();
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return useCase.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.deleteAccount(id);
    }
}