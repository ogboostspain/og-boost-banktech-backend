package es.ogboost.banktech.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Domain model representing a bank account.
 *
 * <p>Contains account details including ID, account number, type, balance,
 * and the customer it belongs to.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    /** Unique identifier of the account */
    private Long id;

    /** Account number (e.g., IBAN or internal identifier) */
    private String accountNumber;

    /** Current balance of the account */
    private BigDecimal balance;

    /** Type of account (e.g., SAVINGS or CHECKING) */
    private String accountType;

    /** Identifier of the customer who owns the account */
    private Long customerId;
}