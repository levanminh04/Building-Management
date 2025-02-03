package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file")
@Getter
@Setter
@Builder
@NoArgsConstructor    // sử dụng @Builder thì
@AllArgsConstructor   // phải kèm 2 annotation này
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "buildingid")
    private Long buildingid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingid", insertable = false, updatable = false)
    private BuildingEntity buildingEntity = new BuildingEntity();
}