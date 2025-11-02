package es.ogboost.banktech.infrastructure.dto;

import es.ogboost.banktech.infrastructure.messages.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;

    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank(message = ValidationMessages.DNI_REQUIRED)
    @Size(min = 8, max = 12)
    private String dni;

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_INVALID)
    private String email;
}