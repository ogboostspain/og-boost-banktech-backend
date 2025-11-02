package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.CustomerUseCase;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.mapper.CustomerMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResponse;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import es.ogboost.banktech.infrastructure.messages.ApiRoutes;
import es.ogboost.banktech.infrastructure.messages.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customers.
 */
@RestController
@RequestMapping(ApiRoutes.CUSTOMERS)
public class CustomerController extends BaseController {

    private final CustomerUseCase useCase;
    private final CustomerMapper mapper;

    public CustomerController(CustomerUseCase useCase, CustomerMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> create(@Valid @RequestBody CustomerDTO dto) {
        Customer created = useCase.createCustomer(mapper.toDomain(dto));
        return respond(ResponseMessages.CUSTOMER_CREATED, mapper.toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getById(@PathVariable Long id) {
        Customer found = useCase.getCustomer(id);
        return respond(ResponseMessages.CUSTOMER_RETRIEVED, mapper.toDto(found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAll() {
        List<Customer> customers = useCase.getAllCustomers();
        return respond(ResponseMessages.CUSTOMER_LIST, mapper.toDtoList(customers));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO dto) {
        Customer updated = useCase.updateCustomer(id, mapper.toDomain(dto));
        return respond(ResponseMessages.CUSTOMER_UPDATED, mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        useCase.deleteCustomer(id);
        return respond(ResponseMessages.CUSTOMER_DELETED, null);
    }
}