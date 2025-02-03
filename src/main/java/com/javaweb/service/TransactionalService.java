package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionalService {
    void addOrUpdateTransaction(TransactionEntity transactionEntity);
    String loadOldNote(Long transactionid);
    List<TransactionDTO> findByCodeAndCustomerid(String code, Long customerid);
}
