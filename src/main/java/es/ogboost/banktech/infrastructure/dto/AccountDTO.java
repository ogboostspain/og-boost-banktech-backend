package es.ogboost.banktech.infrastructure.dto;

import es.ogboost.banktech.infrastructure.messages.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object representing a bank account")
public class AccountDTO {

    @Schema(
            description = "Unique identifier of the account",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = ValidationMessages.ACCOUNT_TYPE_REQUIRED)
    @Schema(
            description = "Type of the account (e.g., Savings, Checking)",
            example = "Savings"
    )
    private String accountType;

    @NotNull(message = ValidationMessages.BALANCE_REQUIRED)
    @Positive(message = ValidationMessages.BALANCE_POSITIVE)
    @Schema(
            description = "Current balance of the account",
            example = "1000.50"
    )
    private BigDecimal balance;

    @Schema(
            description = "Identifier of the customer owning the account",
            example = "1"
    )
    private Long customerId;
}