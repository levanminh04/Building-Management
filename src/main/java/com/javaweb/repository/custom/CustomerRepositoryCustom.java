package com.javaweb.repository.custom;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {
    public List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilders);
}
