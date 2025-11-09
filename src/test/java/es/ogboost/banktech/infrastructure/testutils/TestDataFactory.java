package es.ogboost.banktech.infrastructure.testutils;

import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.domain.model.Account;
import es.ogboost.banktech.infrastructure.adapters.repository.jpa.entity.AccountEntity;
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

    public static Account createAccount(Long id, String accountNumber, String accountType, double balance, Long customerId) {
        Account account = new Account();
        account.setId(id);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(BigDecimal.valueOf(balance));
        account.setCustomerId(customerId);
        return account;
    }

    public static AccountDTO createAccountDTO(Long id, String accountNumber, String accountType, double balance, Long customerId) {
        AccountDTO dto = new AccountDTO();
        dto.setId(id);
        dto.setAccountNumber(accountNumber);
        dto.setAccountType(accountType);
        dto.setBalance(BigDecimal.valueOf(balance));
        dto.setCustomerId(customerId);
        return dto;
    }

    public static Account baseAccount() {
        return createAccount(1L, "ES7620770024003102575766","Savings", 1000.50, 1L);
    }

    public static AccountDTO baseAccountDTO() {
        return createAccountDTO(1L, "ES7620770024003102575766","Savings", 1000.50, 1L);
    }

    public static AccountEntity createAccountEntity(Long id, String accountType, String accountNumber, double balance, Long customerId) {
        return AccountEntity.builder()
                .id(id)
                .accountType(accountType)
                .accountNumber(accountNumber)
                .balance(BigDecimal.valueOf(balance))
                .customerId(customerId)
                .build();
    }

    public static AccountEntity baseAccountEntity() {
        return createAccountEntity(1L, "SAVINGS", "ES7620770024003102575766", 1000.50, 1L);
    }
}