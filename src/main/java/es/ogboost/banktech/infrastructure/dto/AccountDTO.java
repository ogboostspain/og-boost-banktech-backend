package es.ogboost.banktech.infrastructure.dto;

import es.ogboost.banktech.infrastructure.messages.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;

    @NotBlank(message = ValidationMessages.ACCOUNT_TYPE_REQUIRED)
    private String accountType;

    @NotNull(message = ValidationMessages.BALANCE_REQUIRED)
    @Positive(message = ValidationMessages.BALANCE_POSITIVE)
    private BigDecimal balance;

    private Long customerId;
}