package com.javaweb.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", nullable = false, length = 255)
    private String fullname;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email",  length = 255)
    private String email;

    @Column(name = "companyname", length = 255)
    private String companyname;

    @Column(name = "demand", length = 255)
    private String demand;

    @Column(name = "status", length = 50)
    private String status ;

    @Column(name = "is_active")
    private Boolean is_active = true;


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();


}
