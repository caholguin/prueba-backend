package com.spring.prueba.controller;

import com.spring.prueba.DTO.request.FindCustomerDTO;
import com.spring.prueba.DTO.response.CustomerDTO;
import com.spring.prueba.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> SearchByTypeAndNumber(@Valid @RequestBody FindCustomerDTO findCustomerDTO){
        CustomerDTO customerDTO = customerService.searchByTypeAndNumber(findCustomerDTO);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }
}
