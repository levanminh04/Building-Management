package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TransactionConverter {
    private final ModelMapper modelMapper;

    private final CustomerRepository customerRepository;

    private final CookieUtils cookieUtils;

    public TransactionEntity toTransactionEntity(TransactionDTO transactionDTO, HttpServletRequest request) {
        try {
            TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
            CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId())
                    .orElseThrow(() -> new NoSuchElementException("Customer not found for ID: " + transactionDTO.getCustomerId()));

            transactionEntity.setCustomer(customerEntity);
            transactionEntity.setStaffId(cookieUtils.getUserId(request));
            return transactionEntity;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting TransactionDTO to TransactionEntity: " + e.getMessage(), e);
        }
    }
    public TransactionDTO toTransactionDTO(TransactionEntity transactionEntity) {
        try {
            return modelMapper.map(transactionEntity, TransactionDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting TransactionDTO to TransactionEntity: " + e.getMessage(), e);
        }
    }
}
