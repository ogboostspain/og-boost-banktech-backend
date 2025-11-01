package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.CustomerUseCase;
import es.ogboost.banktech.domain.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerUseCase useCase;

    public CustomerController(CustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return useCase.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return useCase.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getAll() {
        return useCase.getAllCustomers();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
        return useCase.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.deleteCustomer(id);
    }
}