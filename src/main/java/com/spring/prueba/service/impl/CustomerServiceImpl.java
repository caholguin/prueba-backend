package com.spring.prueba.service.impl;

import com.spring.prueba.DTO.request.FindCustomerDTO;
import com.spring.prueba.DTO.response.CustomerDTO;
import com.spring.prueba.Exception.ObjectNotFoundException;
import com.spring.prueba.entity.Customer;
import com.spring.prueba.mapper.CustomerMapper;
import com.spring.prueba.repository.CustomerRepository;
import com.spring.prueba.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerDTO searchByTypeAndNumber(FindCustomerDTO findCustomerDTO) {
        Customer customer = customerRepository
                .searchByTypeAndNumber(findCustomerDTO)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente no encontrado"));

        return CustomerMapper.toDto(customer);
    }
}
