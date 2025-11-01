package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.CustomerUseCase;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.controller.response.ApiResponse;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import es.ogboost.banktech.infrastructure.adapters.mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerUseCase useCase;
    private final CustomerMapper mapper;

    public CustomerController(CustomerUseCase useCase, CustomerMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> create(@RequestBody CustomerDTO dto) {
        Customer created = useCase.createCustomer(mapper.toDomain(dto));
        return ResponseEntity.ok(ApiResponse.ok("Customer created successfully", mapper.toDto(created)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getById(@PathVariable Long id) {
        Customer found = useCase.getCustomer(id);
        return ResponseEntity.ok(ApiResponse.ok("Customer retrieved successfully", mapper.toDto(found)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAll() {
        List<Customer> customers = useCase.getAllCustomers();
        return ResponseEntity.ok(ApiResponse.ok("List of all customers", mapper.toDtoList(customers)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Customer updated = useCase.updateCustomer(id, mapper.toDomain(dto));
        return ResponseEntity.ok(ApiResponse.ok("Customer updated successfully", mapper.toDto(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        useCase.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.ok("Customer deleted successfully", null));
    }
}