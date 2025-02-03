package com.javaweb.service;

import java.util.List;

public interface AssignmentCustomerService {
    void assignCustomer(Long customerId, List<Long> staffs);
}
