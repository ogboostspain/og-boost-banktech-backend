package es.ogboost.banktech.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain model representing a customer.
 *
 * <p>Contains personal information such as name, DNI, and email,
 * along with a unique identifier.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    /** Unique identifier of the customer */
    private Long id;

    /** First name of the customer */
    private String firstName;

    /** Last name of the customer */
    private String lastName;

    /** DNI (National Identification Document) of the customer */
    private String dni;

    /** Email address of the customer */
    private String email;
}