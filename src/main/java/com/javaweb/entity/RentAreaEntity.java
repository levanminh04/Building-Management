package com.javaweb.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "rentarea")
@Data
public class RentAreaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "buildingid")   // to indicate the 'foreign key' column in the database (foreign key column in the RentAreaEntity), nó sẽ ánh sẽ đến cái cột được đánh dấu @Id ở BuildingEntity
    @ToString.Exclude
    private BuildingEntity building;   // tên này phải giống với tên được mappedBy ở bên BuildingEntity

    // Hibernate nhận biết rằng bảng "nhiều" (e.g., RentAreaEntity) có một cột khóa ngoại liên kết với bảng "một" (e.g., BuildingEntity).
    // Hibernate kiểm tra giá trị của mappedBy để hiểu rằng mối quan hệ đã được định nghĩa ở phía "nhiều", và khóa ngoại đang nằm trong bảng đó.



}

