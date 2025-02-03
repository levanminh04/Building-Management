package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomerConverter {


    private final ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){

        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }
}
