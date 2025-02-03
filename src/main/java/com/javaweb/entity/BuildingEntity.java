package com.javaweb.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "building")
@Data
public class BuildingEntity extends BaseEntity {

    @Id   // thể hiện attribute được đánh dấu là khóa chính, không cần thêm @Column, khóa chính thì mặc định NOT NULL
    @GeneratedValue(strategy = GenerationType.IDENTITY) // thể hiện AI: auto increment
    private Long id;

    @Column(name = "name")
    private String name;

	@Column(name = "district")
	private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "rentprice")  // Trong Hibernate, tên của cột trong @Column cần giống chính xác với tên của cột trong database,
    private Long rentPrice;   	 // bao gồm phân biệt chữ hoa và chữ thường.

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "brokeragefee")
    private Long brokerageFee;

    @Column(name = "type")
    private String type;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "note")
    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();


    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    @ToString.Exclude
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

    // muốn đọc comment giải thích các từ khóa thì vào branch manual ManyToMany mà đọc



    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}
            , orphanRemoval = true)
    private List<FileEntity> fileEntities = new ArrayList<>();
}
