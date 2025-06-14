package com.spring.prueba.DTO.request;

import jakarta.validation.constraints.*;

public class FindCustomerDTO {


    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "[CcPp]", message = "Type must be 'C' or 'P'")
    String type;

    @NotBlank(message = "Number is mandatory")
    @Size(min = 8, max = 11, message = "Number must be between 8 and 11 characters")
    String numberDocument;

    public FindCustomerDTO(String type, String numberDocument){
        this.type = type;
        this.numberDocument = numberDocument;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getNumberDocument(){
        return numberDocument;
    }

    public void setNumberDocument(String numberDocument){
        this.numberDocument = numberDocument;
    }
}
