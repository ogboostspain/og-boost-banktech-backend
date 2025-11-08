package es.ogboost.banktech.infrastructure.controller;

import es.ogboost.banktech.application.usecases.CustomerUseCase;
import es.ogboost.banktech.domain.model.Customer;
import es.ogboost.banktech.infrastructure.adapters.mapper.CustomerMapper;
import es.ogboost.banktech.infrastructure.controller.response.ApiResult;
import es.ogboost.banktech.infrastructure.dto.CustomerDTO;
import es.ogboost.banktech.infrastructure.messages.ResponseMessages;
import es.ogboost.banktech.infrastructure.testutils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerUseCase customerUseCase;

    @Mock
    private CustomerMapper customerMapper;

    private AutoCloseable closeable;

    private Customer customer;
    private CustomerDTO customerDTO;
    private Customer updatedCustomer;
    private CustomerDTO updatedCustomerDTO;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        customer = TestDataFactory.baseCustomer();
        customerDTO = TestDataFactory.baseCustomerDTO();

        updatedCustomer = TestDataFactory.createCustomer(
                1L, "Jane", "Doe", "jane.doe@example.com", "12345678A"
        );
        updatedCustomerDTO = TestDataFactory.createCustomerDTO(
                1L, "Jane", "Doe", "jane.doe@example.com", "12345678A"
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void create_ShouldReturnCreatedCustomer() {
        when(customerMapper.toDomain(customerDTO)).thenReturn(customer);
        when(customerUseCase.createCustomer(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        ResponseEntity<ApiResult<CustomerDTO>> response = customerController.create(customerDTO);
        ApiResult<CustomerDTO> body = response.getBody();
        assertNotNull(body);
        assertEquals(ResponseMessages.CUSTOMER_CREATED, body.getMessage());
        assertTrue(body.isSuccess());
        assertEquals(customerDTO, body.getData());

        verify(customerUseCase, times(1)).createCustomer(customer);
        verify(customerMapper, times(1)).toDomain(customerDTO);
        verify(customerMapper, times(1)).toDto(customer);
    }

    @Test
    void getById_ShouldReturnCustomer() {
        when(customerUseCase.getCustomer(1L)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        ResponseEntity<ApiResult<CustomerDTO>> response = customerController.getById(1L);
        ApiResult<CustomerDTO> body = response.getBody();
        assertNotNull(body);
        assertEquals(ResponseMessages.CUSTOMER_RETRIEVED, body.getMessage());
        assertEquals(customerDTO, body.getData());

        verify(customerUseCase, times(1)).getCustomer(1L);
        verify(customerMapper, times(1)).toDto(customer);
    }

    @Test
    void getAll_ShouldReturnListOfCustomers() {
        List<Customer> customers = List.of(customer);
        List<CustomerDTO> dtos = List.of(customerDTO);

        when(customerUseCase.getAllCustomers()).thenReturn(customers);
        when(customerMapper.toDtoList(customers)).thenReturn(dtos);

        ResponseEntity<ApiResult<List<CustomerDTO>>> response = customerController.getAll();
        ApiResult<List<CustomerDTO>> body = response.getBody();
        assertNotNull(body);
        assertEquals(ResponseMessages.CUSTOMER_LIST, body.getMessage());
        assertEquals(dtos, body.getData());

        verify(customerUseCase, times(1)).getAllCustomers();
        verify(customerMapper, times(1)).toDtoList(customers);
    }

    @Test
    void update_ShouldReturnUpdatedCustomer() {
        when(customerMapper.toDomain(updatedCustomerDTO)).thenReturn(updatedCustomer);
        when(customerUseCase.updateCustomer(1L, updatedCustomer)).thenReturn(updatedCustomer);
        when(customerMapper.toDto(updatedCustomer)).thenReturn(updatedCustomerDTO);

        ResponseEntity<ApiResult<CustomerDTO>> response = customerController.update(1L, updatedCustomerDTO);
        ApiResult<CustomerDTO> body = response.getBody();
        assertNotNull(body);
        assertEquals(ResponseMessages.CUSTOMER_UPDATED, body.getMessage());
        assertEquals(updatedCustomerDTO, body.getData());

        verify(customerMapper, times(1)).toDomain(updatedCustomerDTO);
        verify(customerUseCase, times(1)).updateCustomer(1L, updatedCustomer);
        verify(customerMapper, times(1)).toDto(updatedCustomer);
    }

    @Test
    void delete_ShouldReturnVoidResponse() {
        doNothing().when(customerUseCase).deleteCustomer(1L);

        ResponseEntity<ApiResult<Void>> response = customerController.delete(1L);
        ApiResult<Void> body = response.getBody();
        assertNotNull(body);
        assertEquals(ResponseMessages.CUSTOMER_DELETED, body.getMessage());
        assertNull(body.getData());

        verify(customerUseCase, times(1)).deleteCustomer(1L);
    }

    // -------- Tests de errores --------

    @Test
    void getById_ShouldThrowException_WhenCustomerNotFound() {
        when(customerUseCase.getCustomer(999L)).thenThrow(new RuntimeException("Customer not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerController.getById(999L);
        });
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void update_ShouldThrowException_WhenCustomerNotFound() {
        when(customerMapper.toDomain(updatedCustomerDTO)).thenReturn(updatedCustomer);
        when(customerUseCase.updateCustomer(999L, updatedCustomer)).thenThrow(new RuntimeException("Customer not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerController.update(999L, updatedCustomerDTO);
        });
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void delete_ShouldThrowException_WhenCustomerNotFound() {
        doThrow(new RuntimeException("Customer not found")).when(customerUseCase).deleteCustomer(999L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerController.delete(999L);
        });
        assertEquals("Customer not found", exception.getMessage());
    }
}