package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentCustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void assignCustomer(Long customerId, List<Long> staffs) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElse(null);

        List<UserEntity> list = new ArrayList<>();
        for(Long staffId : staffs) {
            UserEntity staffEntity = userRepository.findById(staffId).orElse(null);
            list.add(staffEntity);
        }
        customerEntity.setUsers(list);
        customerRepository.save(customerEntity);
    }
}
