package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class TransactionalServiceImpl implements TransactionalService {

    private final TransactionRepository transactionRepository;

    private final TransactionConverter transactionConverter;

    @Override

    public void addOrUpdateTransaction(TransactionEntity transactionEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("check tai day: " + authentication);
            transactionRepository.save(transactionEntity);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Failed to save transaction to the database", e);
        }
    }

    @Override
    public String loadOldNote(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(TransactionEntity::getNote) // .map() của Optional được sử dụng để biến đổi (transform) giá trị bên trong Optional nếu nó tồn tại (not null). Kết quả là một Optional mới chứa giá trị đã được biến đổi. Nếu giá trị ban đầu không tồn tại (Optional.empty()), thì .map() sẽ không thực thi hàm truyền vào và chỉ trả về Optional.empty().
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found for ID: " + transactionId));
    }           // .map(TransactionEntity::getNote) trả về 1 Optinal<String>, Nếu giá trị tồn tại, .orElseThrow() sẽ trả về giá trị bên trong (ở đây là String). Nếu không, nó sẽ ném ngoại lệ mà bạn cung cấp.

    @Override
    public List<TransactionDTO> findByCodeAndCustomerid(String code, Long customerid) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCodeAndCustomer_Id(code, customerid);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntities) {
            transactionDTOS.add(transactionConverter.toTransactionDTO(transactionEntity));
        }
        return transactionDTOS;
    }

}
