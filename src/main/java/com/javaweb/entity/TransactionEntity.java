package com.javaweb.entity;

import com.javaweb.entity.BaseEntity;
import com.javaweb.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 255)
    private String code;

    @Column(name = "note", length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private CustomerEntity customer;


    @Column(name = "staffid")
    private Long staffId;
}
