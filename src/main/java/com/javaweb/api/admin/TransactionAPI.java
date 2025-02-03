package com.javaweb.api.admin;


import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.service.TransactionalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionAPI {

    private final TransactionalService transactionalService;

    private final TransactionConverter transactionConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> loadOldNote(@PathVariable Long id) {
        try {
            String note = transactionalService.loadOldNote(id);

            if (note == null || note.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Note not found for the given ID."));
            }

            return ResponseEntity.ok(Map.of("note", note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request) {
        try {
            TransactionEntity transactionEntity = transactionConverter.toTransactionEntity(transactionDTO, request);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("check tai day 2: " + authentication);
            transactionalService.addOrUpdateTransaction(transactionEntity);
            return ResponseEntity.ok(Map.of("message", "Transaction processed successfully"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Customer not found"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unexpected error occurred: " + e.getMessage()));
        }
    }
}
