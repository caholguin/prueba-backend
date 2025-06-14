package com.spring.prueba.DTO.response;

public class CustomerDTO {

    private String firstName;
    private String middleName;
    private String firstLastName;
    private String secondLastName;
    private String phoneNumber;
    private String address;
    private String cityOfResidence;

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getMiddleName(){
        return middleName;
    }

    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    public String getFirstLastName(){
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName){
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName(){
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName){
        this.secondLastName = secondLastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCityOfResidence(){
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence){
        this.cityOfResidence = cityOfResidence;
    }
}
