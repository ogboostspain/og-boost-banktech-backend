package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.infrastructure.controller.response.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * Base controller to provide common functionality like standardized API responses.
 */
public abstract class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> respond(String message, T data) {
        return ResponseEntity.ok(ApiResponse.ok(message, data));
    }
}