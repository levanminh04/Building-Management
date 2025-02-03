package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.response.CustomerSearchResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerSearchResponseConverter {

    private final ModelMapper modelMapper;

    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customerEntity) {


        CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity, CustomerSearchResponse.class);


        return customerSearchResponse;
    }
}
