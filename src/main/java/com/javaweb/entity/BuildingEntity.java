package com.javaweb.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "building")
public class BuildingEntity {

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),           // joinColumns: Thuộc tính này xác định cột trong bảng trung gian sẽ chứa khóa ngoại liên kết với THỰC THỂ HIỆN TẠI (buildingEntity).
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))     // inverseJoinColumns xác định khóa ngoại trỏ về thực thể đối nghịch (ở đây là UserEntity).
    private List<UserEntity> users = new ArrayList<>();


    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY) // mappedBy = "building" refers to the "building" field in the RentAreaEntity class, bên RentAreaEntity cũng phải có một field tên giống hệt với mappedBy
                                                              // mappedBy = "building" thể hiện RentAreaEntity có khóa ngoại của Building
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();   // fetch = FetchType.LAZY: hiểu đơn giản thì đây là chế độ đọc, ta đang chọn LAZY LOAD

    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();


    public List<AssignmentBuildingEntity> getAssignmentBuildingEntities() {
        return assignmentBuildingEntities;
    }

    public void setAssignmentBuildingEntities(List<AssignmentBuildingEntity> assignmentBuildingEntities) {
        this.assignmentBuildingEntities = assignmentBuildingEntities;
    }

    public List<RentAreaEntity> getRentAreaEntities() {
        return rentAreaEntities;
    }

    public void setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) {
        this.rentAreaEntities = rentAreaEntities;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }
    public Long getBrokerageFee() {
        return brokerageFee;
    }
    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
