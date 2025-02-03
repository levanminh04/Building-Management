package com.javaweb.repository;

import com.javaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCodeAndCustomer_Id(String code, Long customer_id);
}
