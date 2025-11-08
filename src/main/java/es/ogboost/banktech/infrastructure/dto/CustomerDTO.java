package es.ogboost.banktech.infrastructure.dto;

import es.ogboost.banktech.infrastructure.messages.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object representing a customer.
 *
 * <p>This DTO is used to transfer customer data in API requests and responses.
 * It includes validation constraints to ensure required fields are provided and
 * conform to expected formats. The fields are documented for OpenAPI/Swagger generation.</p>
 *
 * <ul>
 *     <li>{@code id}: Read-only unique identifier of the customer.</li>
 *     <li>{@code firstName}: Required, minimum 2 and maximum 50 characters.</li>
 *     <li>{@code lastName}: Required, minimum 2 and maximum 50 characters.</li>
 *     <li>{@code dni}: Required, minimum 8 and maximum 12 characters.</li>
 *     <li>{@code email}: Required, must be a valid email format.</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object representing a customer")
public class CustomerDTO {

    /**
     * Unique identifier of the customer (read-only).
     */
    @Schema(description = "Unique identifier of the customer", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /**
     * Customer's first name.
     */
    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    @Size(min = 2, max = 50)
    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    /**
     * Customer's last name.
     */
    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    @Size(min = 2, max = 50)
    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    /**
     * Customer's DNI (national identification number).
     */
    @NotBlank(message = ValidationMessages.DNI_REQUIRED)
    @Size(min = 8, max = 12)
    @Schema(description = "Customer's DNI (national identification number)", example = "12345678A")
    private String dni;

    /**
     * Customer's email address.
     */
    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_INVALID)
    @Schema(description = "Customer's email address", example = "john.doe@example.com")
    private String email;
}