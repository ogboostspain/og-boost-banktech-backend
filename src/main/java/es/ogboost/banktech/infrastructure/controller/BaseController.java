package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Base controller providing common functionality for REST controllers.
 *
 * <p>This class provides a standardized way to return API responses
 * wrapped in {@link ApiResult} objects. All controllers extending this
 * class can use the {@link #respond(String, Object)} method to return
 * consistent responses for success and error cases.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * return respond("Account created successfully", accountDto);
 * </pre>
 *
 * @param <T> the type of the response data
 */
@Schema(description = "Base controller providing standardized API responses")
public abstract class BaseController {

    /**
     * Returns a standardized API response wrapped in {@link ApiResult}.
     *
     * @param message a descriptive message of the result
     * @param data    the payload of the response, can be {@code null} if no data
     * @param <T>     type of the response data
     * @return {@link ResponseEntity} containing an {@link ApiResult} with the data and message
     */
    protected <T> ResponseEntity<ApiResult<T>> respond(String message, T data) {
        return ResponseEntity.ok(ApiResult.ok(message, data));
    }
}