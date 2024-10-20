package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuildingEntityIdIn(List<Long> assignmentId);
    void deleteByBuildingEntityId(Long id); // 1 building id có thể được gán cho nhiều bản ghi của assignmentbuilding, nên ghi xóa thì nó sẽ xóa tất cả các bản ghi có buildingid ta truyền vào chứ không phải chỉ xóa 1 bản ghi
}
