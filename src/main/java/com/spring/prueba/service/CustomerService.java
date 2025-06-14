package com.spring.prueba.service;

import com.spring.prueba.DTO.request.FindCustomerDTO;
import com.spring.prueba.DTO.response.CustomerDTO;

public interface CustomerService {

    CustomerDTO searchByTypeAndNumber(FindCustomerDTO findCustomerDTO);
}
