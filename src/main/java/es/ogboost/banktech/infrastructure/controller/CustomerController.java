package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.CustomerUseCase;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.mapper.CustomerMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import es.ogboost.banktech.infrastructure.messages.ApiRoutes;
import es.ogboost.banktech.infrastructure.messages.ResponseMessages;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST controller for managing bank customers.
 *
 * <p>Provides endpoints to create, retrieve, update, and delete customers.
 * Responses are wrapped in {@link ApiResult} objects with {@code success}, {@code message}, and {@code data}.</p>
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

    /**
     * Creates a new customer.
     *
     * @param dto CustomerDTO containing customer details.
     * @return ApiResult with the created customer.
     */
    @PostMapping
    @Operation(
            summary = "Create a new customer",
            description = "Creates a new customer with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer successfully created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Validation failed",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<CustomerDTO>> create(@Valid @RequestBody CustomerDTO dto) {
        Customer created = useCase.createCustomer(mapper.toDomain(dto));
        return respond(ResponseMessages.CUSTOMER_CREATED, mapper.toDto(created));
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id Customer ID.
     * @return ApiResult with the requested customer.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get customer by ID",
            description = "Retrieves the customer corresponding to the given ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<CustomerDTO>> getById(@PathVariable Long id) {
        Customer found = useCase.getCustomer(id);
        return respond(ResponseMessages.CUSTOMER_RETRIEVED, mapper.toDto(found));
    }

    /**
     * Retrieves all customers.
     *
     * @return ApiResult with a list of all customers.
     */
    @GetMapping
    @Operation(
            summary = "Get all customers",
            description = "Retrieves a list of all customers.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customers retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<List<CustomerDTO>>> getAll() {
        List<Customer> customers = useCase.getAllCustomers();
        return respond(ResponseMessages.CUSTOMER_LIST, mapper.toDtoList(customers));
    }

    /**
     * Updates an existing customer.
     *
     * @param id  Customer ID.
     * @param dto CustomerDTO with updated details.
     * @return ApiResult with the updated customer.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a customer",
            description = "Updates the customer with the given ID using the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Validation failed",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<CustomerDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO dto) {
        Customer updated = useCase.updateCustomer(id, mapper.toDomain(dto));
        return respond(ResponseMessages.CUSTOMER_UPDATED, mapper.toDto(updated));
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id Customer ID.
     * @return ApiResult confirming deletion.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a customer",
            description = "Deletes the customer with the specified ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer deleted successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResult.class)
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<Void>> delete(@PathVariable Long id) {
        useCase.deleteCustomer(id);
        return respond(ResponseMessages.CUSTOMER_DELETED, null);
    }
}