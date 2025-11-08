package es.ogboost.banktech.infrastructure.controller.advice;

import es.ogboost.banktech.domain.exceptions.AccountNotFoundException;
import es.ogboost.banktech.domain.exceptions.CustomerNotFoundException;
import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for REST controllers.
 *
 * <p>This class handles exceptions thrown by any controller in the application and
 * converts them into standardized {@link ApiResult} responses, providing consistent
 * error messages and HTTP status codes.</p>
 *
 * <p>Common exceptions handled include:</p>
 * <ul>
 *     <li>EntityNotFoundException → 404 NOT FOUND</li>
 *     <li>CustomerNotFoundException → 404 NOT FOUND</li>
 *     <li>AccountNotFoundException → 404 NOT FOUND</li>
 *     <li>IllegalArgumentException → 400 BAD REQUEST</li>
 *     <li>MethodArgumentNotValidException → 422 UNPROCESSABLE ENTITY</li>
 *     <li>SecurityException → 403 FORBIDDEN</li>
 *     <li>RuntimeException → 500 INTERNAL SERVER ERROR</li>
 *     <li>Generic Exception → 500 INTERNAL SERVER ERROR with details</li>
 * </ul>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic JPA EntityNotFoundException.
     *
     * @param ex the exception thrown
     * @return standardized ApiResult with error message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResult<Void>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResult.error(ExceptionMessages.ENTITY_NOT_FOUND + ": " + ex.getMessage()));
    }

    /**
     * Handles CustomerNotFoundException.
     *
     * @param ex the exception thrown
     * @return standardized ApiResult with error message
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResult<Void>> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResult.error(ExceptionMessages.CUSTOMER_NOT_FOUND + ": " + ex.getMessage()));
    }

    /**
     * Handles AccountNotFoundException.
     *
     * @param ex the exception thrown
     * @return standardized ApiResult with error message
     */
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResult<Void>> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResult.error(ExceptionMessages.ACCOUNT_NOT_FOUND + ": " + ex.getMessage()));
    }

    /**
     * Handles invalid arguments (IllegalArgumentException).
     *
     * @param ex the exception thrown
     * @return standardized ApiResult with error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResult<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResult.error(ExceptionMessages.INVALID_REQUEST + ": " + ex.getMessage()));
    }

    /**
     * Handles validation errors from @Valid annotated DTOs.
     *
     * @param ex the validation exception
     * @return ApiResult containing a map of field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ApiResult<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResult.error(ExceptionMessages.VALIDATION_FAILED, errors));
    }

    /**
     * Handles SecurityException, e.g., access denied.
     *
     * @param ex the security exception
     * @return ApiResult with forbidden status
     */
    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiResult<Void>> handleSecurity(SecurityException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResult.error(ExceptionMessages.ACCESS_DENIED + ": " + ex.getMessage()));
    }

    /**
     * Handles runtime exceptions.
     *
     * @param ex the runtime exception
     * @param request the web request
     * @return ApiResult with internal server error
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResult<Void>> handleRuntime(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.error(ExceptionMessages.UNEXPECTED_ERROR + ": " + ex.getMessage()));
    }

    /**
     * Handles all other generic exceptions.
     *
     * <p>Includes timestamp, request path, and exception class in the response details.</p>
     *
     * @param ex the exception
     * @param request the web request
     * @return ApiResult containing detailed error information
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResult<Map<String, Object>>> handleGeneric(Exception ex, WebRequest request) {
        Map<String, Object> details = new HashMap<>();
        details.put("timestamp", LocalDateTime.now());
        details.put("path", request.getDescription(false));
        details.put("error", ex.getClass().getSimpleName());

        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.error(ExceptionMessages.UNHANDLED_EXCEPTION + ": " + ex.getMessage(), details));
    }
}