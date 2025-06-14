package com.spring.prueba.repository;

import com.spring.prueba.DTO.request.FindCustomerDTO;
import com.spring.prueba.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    public CustomerRepository() {
        Customer customer = new Customer();
        customer.setFirstName("Carlos");
        customer.setMiddleName("Alberto");
        customer.setFirstLastName("Holguín");
        customer.setSecondLastName("Arboleda");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("Calle 123 #45-67");
        customer.setCityOfResidence("Medellín");

        customers.put("C-23445322", customer);
    }

    public Optional<Customer> searchByTypeAndNumber(FindCustomerDTO findCustomerDTO) {
        return Optional.ofNullable(customers.get(findCustomerDTO.getType().toUpperCase() + "-"+findCustomerDTO.getNumberDocument()));
    }
}