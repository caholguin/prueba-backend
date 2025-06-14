package com.spring.prueba.service;

import com.spring.prueba.DTO.request.FindCustomerDTO;
import com.spring.prueba.DTO.response.CustomerDTO;
import com.spring.prueba.Exception.ObjectNotFoundException;
import com.spring.prueba.entity.Customer;
import com.spring.prueba.repository.CustomerRepository;
import com.spring.prueba.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void shouldReturnCustomerWhenExists() {
        FindCustomerDTO request = new FindCustomerDTO("C", "23445322");

        Customer customer = new Customer();
        customer.setFirstName("Carlos");
        customer.setMiddleName("Alberto");
        customer.setFirstLastName("Holguín");
        customer.setSecondLastName("Arboleda");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("Calle 123 #45-67");
        customer.setCityOfResidence("Medellín");

        when(customerRepository.searchByTypeAndNumber(request)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.searchByTypeAndNumber(request);

        assertNotNull(result);
        assertEquals("Carlos", result.getFirstName());
        assertEquals("Alberto", result.getMiddleName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        FindCustomerDTO request = new FindCustomerDTO("c", "99999999");

        when(customerRepository.searchByTypeAndNumber(request)).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            customerService.searchByTypeAndNumber(request);
        });

        assertEquals("Customer not found", exception.getMessage());
    }
}
