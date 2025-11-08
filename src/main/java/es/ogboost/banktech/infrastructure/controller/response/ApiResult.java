package es.ogboost.banktech.infrastructure.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResult<T> ok(String message, T data) {
        return new ApiResult<>(true, message, data);
    }

    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<>(false, message, null);
    }

    public static <T> ApiResult<T> error(String message, T data) {
        return new ApiResult<>(false, message, data);
    }
}