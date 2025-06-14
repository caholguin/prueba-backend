package com.spring.prueba.mapper;

import com.spring.prueba.DTO.response.CustomerDTO;
import com.spring.prueba.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static CustomerDTO toDto(Customer customer){
        if (customer == null) return null;

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setMiddleName(customer.getMiddleName());
        customerDTO.setFirstLastName(customer.getFirstLastName());
        customerDTO.setSecondLastName(customer.getSecondLastName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCityOfResidence(customer.getCityOfResidence());

        return customerDTO;
    }
}