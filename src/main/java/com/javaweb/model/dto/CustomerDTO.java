package com.javaweb.model.dto;


import lombok.Data;

@Data
public class CustomerDTO extends AbstractDTO{
    private String fullname;
    private String managementStaff;
    private String phone;
    private String email;
    private String demand;
    private String status;
    private String companyname;


}
