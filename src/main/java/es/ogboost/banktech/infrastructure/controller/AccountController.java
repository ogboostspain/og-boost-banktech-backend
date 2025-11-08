package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.AccountUseCase;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.mapper.AccountMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;
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
 * REST controller for managing bank accounts.
 *
 * <p>Provides endpoints to create, retrieve, update, and delete accounts.
 * Responses are wrapped in {@link ApiResult} objects with {@code success}, {@code message}, and {@code data}.</p>
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

    /**
     * Creates a new account.
     *
     * @param dto AccountDTO containing account details.
     * @return ApiResult with the created account.
     */
    @PostMapping
    @Operation(
            summary = "Create a new account",
            description = "Creates a new bank account with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account successfully created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": true,\n" +
                                                    "  \"message\": \"Account created successfully\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"id\": 1,\n" +
                                                    "    \"accountType\": \"Savings\",\n" +
                                                    "    \"balance\": 1000.50,\n" +
                                                    "    \"customerId\": 1\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<AccountDTO>> create(@Valid @RequestBody AccountDTO dto) {
        Account created = useCase.createAccount(mapper.toDomain(dto));
        return respond(ResponseMessages.ACCOUNT_CREATED, mapper.toDto(created));
    }

    /**
     * Retrieves an account by ID.
     *
     * @param id Account ID.
     * @return ApiResult with the requested account.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get account by ID",
            description = "Retrieves the account corresponding to the given ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": true,\n" +
                                                    "  \"message\": \"Account retrieved successfully\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"id\": 1,\n" +
                                                    "    \"accountType\": \"Savings\",\n" +
                                                    "    \"balance\": 1000.50,\n" +
                                                    "    \"customerId\": 1\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": false,\n" +
                                                    "  \"message\": \"Account not found: Account not found id=8\",\n" +
                                                    "  \"data\": null\n" +
                                                    "}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<AccountDTO>> getById(@PathVariable Long id) {
        return respond(ResponseMessages.ACCOUNT_RETRIEVED, mapper.toDto(useCase.getAccount(id)));
    }

    /**
     * Retrieves all accounts.
     *
     * @return ApiResult with a list of all accounts.
     */
    @GetMapping
    @Operation(
            summary = "Get all accounts",
            description = "Retrieves a list of all accounts.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Accounts retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": true,\n" +
                                                    "  \"message\": \"Account list retrieved successfully\",\n" +
                                                    "  \"data\": [\n" +
                                                    "    {\n" +
                                                    "      \"id\": 1,\n" +
                                                    "      \"accountType\": \"Savings\",\n" +
                                                    "      \"balance\": 1000.50,\n" +
                                                    "      \"customerId\": 1\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"id\": 2,\n" +
                                                    "      \"accountType\": \"Checking\",\n" +
                                                    "      \"balance\": 500.00,\n" +
                                                    "      \"customerId\": 2\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<List<AccountDTO>>> getAll() {
        return respond(ResponseMessages.ACCOUNT_LIST, mapper.toDtoList(useCase.getAllAccounts()));
    }

    /**
     * Updates an existing account.
     *
     * @param id  Account ID.
     * @param dto AccountDTO with updated details.
     * @return ApiResult with the updated account.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an account",
            description = "Updates the account with the given ID using the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": true,\n" +
                                                    "  \"message\": \"Account updated successfully\",\n" +
                                                    "  \"data\": {\n" +
                                                    "    \"id\": 1,\n" +
                                                    "    \"accountType\": \"Savings\",\n" +
                                                    "    \"balance\": 1200.00,\n" +
                                                    "    \"customerId\": 1\n" +
                                                    "  }\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": false,\n" +
                                                    "  \"message\": \"Account not found: Account not found id=8\",\n" +
                                                    "  \"data\": null\n" +
                                                    "}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<AccountDTO>> update(@PathVariable Long id, @Valid @RequestBody AccountDTO dto) {
        return respond(ResponseMessages.ACCOUNT_UPDATED, mapper.toDto(useCase.updateAccount(id, mapper.toDomain(dto))));
    }

    /**
     * Deletes an account by ID.
     *
     * @param id Account ID.
     * @return ApiResult confirming deletion.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an account",
            description = "Deletes the account with the specified ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account deleted successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": true,\n" +
                                                    "  \"message\": \"Account deleted successfully\",\n" +
                                                    "  \"data\": null\n" +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Account not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ApiResult.class,
                                            example = "{\n" +
                                                    "  \"success\": false,\n" +
                                                    "  \"message\": \"Account not found: Account not found id=8\",\n" +
                                                    "  \"data\": null\n" +
                                                    "}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<ApiResult<Void>> delete(@PathVariable Long id) {
        useCase.deleteAccount(id);
        return respond(ResponseMessages.ACCOUNT_DELETED, null);
    }
}