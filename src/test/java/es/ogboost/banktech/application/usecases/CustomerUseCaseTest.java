package es.ogboost.banktech.application.usecases;

import es.ogboost.banktech.application.ports.CustomerRepositoryPort;
import es.ogboost.banktech.domain.exceptions.CustomerNotFoundException;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.messages.ExceptionMessages;
import es.ogboost.banktech.infrastructure.testutils.TestDataFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerUseCaseTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @InjectMocks
    private CustomerUseCase customerUseCase;

    private AutoCloseable closeable;
    private Customer customer;
    private Customer updatedCustomer;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        customer = TestDataFactory.baseCustomer();
        updatedCustomer = TestDataFactory.createCustomer(
                1L, "Jane", "Doe", "jane.doe@example.com", "98765432B"
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createCustomer_ShouldSaveAndReturnCustomer() {
        when(customerRepositoryPort.save(customer)).thenReturn(customer);

        Customer result = customerUseCase.createCustomer(customer);

        assertNotNull(result);
        assertEquals(customer, result);
        verify(customerRepositoryPort, times(1)).save(customer);
    }

    @Test
    void getCustomer_ShouldReturnExistingCustomer() {
        when(customerRepositoryPort.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerUseCase.getCustomer(1L);

        assertNotNull(result);
        assertEquals(customer, result);
        verify(customerRepositoryPort, times(1)).findById(1L);
    }

    @Test
    void getCustomer_ShouldThrow_WhenNotFound() {
        when(customerRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class,
                () -> customerUseCase.getCustomer(99L));

        assertTrue(ex.getMessage().contains(ExceptionMessages.CUSTOMER_NOT_FOUND));
        verify(customerRepositoryPort, times(1)).findById(99L);
    }

    @Test
    void getAllCustomers_ShouldReturnList() {
        when(customerRepositoryPort.findAll()).thenReturn(List.of(customer));

        List<Customer> result = customerUseCase.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
        verify(customerRepositoryPort, times(1)).findAll();
    }

    @Test
    void updateCustomer_ShouldModifyExistingCustomer() {
        when(customerRepositoryPort.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepositoryPort.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerUseCase.updateCustomer(1L, updatedCustomer);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("jane.doe@example.com", result.getEmail());
        assertEquals("98765432B", result.getDni());

        verify(customerRepositoryPort, times(1)).findById(1L);
        verify(customerRepositoryPort, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomer_ShouldThrow_WhenCustomerNotFound() {
        when(customerRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class,
                () -> customerUseCase.updateCustomer(1L, updatedCustomer));

        assertTrue(ex.getMessage().contains(ExceptionMessages.CUSTOMER_NOT_FOUND));
        verify(customerRepositoryPort, times(1)).findById(1L);
        verify(customerRepositoryPort, never()).save(any());
    }

    @Test
    void deleteCustomer_ShouldRemoveCustomer() {
        when(customerRepositoryPort.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepositoryPort).deleteById(1L);

        customerUseCase.deleteCustomer(1L);

        verify(customerRepositoryPort, times(1)).findById(1L);
        verify(customerRepositoryPort, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomer_ShouldThrow_WhenCustomerNotFound() {
        when(customerRepositoryPort.findById(42L)).thenReturn(Optional.empty());

        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class,
                () -> customerUseCase.deleteCustomer(42L));

        assertTrue(ex.getMessage().contains(ExceptionMessages.CUSTOMER_NOT_FOUND));
        verify(customerRepositoryPort, times(1)).findById(42L);
        verify(customerRepositoryPort, never()).deleteById(anyLong());
    }
}