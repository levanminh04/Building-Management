package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerEntity insert(CustomerEntity customerEntity);
    List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest);
    CustomerEntity findById(Long id);
    ResponseDTO loadSfaffs(Long id);
    void deleteCustomers(List<Long> ids);
    void addOrUpdateCustomer(CustomerEntity customerEntity);
}
