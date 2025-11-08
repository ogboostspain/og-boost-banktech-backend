package es.ogboost.banktech.infrastructure.testutils;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import es.ogboost.banktech.infrastructure.dto.AccountDTO;

import java.math.BigDecimal;

public class TestDataFactory {

    public static Customer createCustomer(Long id, String firstName, String lastName, String email, String dni) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setDni(dni);
        return customer;
    }

    public static CustomerDTO createCustomerDTO(Long id, String firstName, String lastName, String email, String dni) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setDni(dni);
        return dto;
    }

    public static Customer baseCustomer() {
        return createCustomer(1L, "John", "Doe", "john.doe@example.com", "12345678A");
    }

    public static CustomerDTO baseCustomerDTO() {
        return createCustomerDTO(1L, "John", "Doe", "john.doe@example.com", "12345678A");
    }

    public static Account createAccount(Long id, String accountType, double balance, Long customerId) {
        Account account = new Account();
        account.setId(id);
        account.setAccountType(accountType);
        account.setBalance(BigDecimal.valueOf(balance));
        account.setCustomerId(customerId);
        return account;
    }

    public static AccountDTO createAccountDTO(Long id, String accountType, double balance, Long customerId) {
        AccountDTO dto = new AccountDTO();
        dto.setId(id);
        dto.setAccountType(accountType);
        dto.setBalance(BigDecimal.valueOf(balance));
        dto.setCustomerId(customerId);
        return dto;
    }

    public static Account baseAccount() {
        return createAccount(1L, "Savings", 1000.50, 1L);
    }

    public static AccountDTO baseAccountDTO() {
        return createAccountDTO(1L, "Savings", 1000.50, 1L);
    }
}