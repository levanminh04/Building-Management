package com.javaweb.repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{
//    BuildingEntity findById(Long buildingid);
//    những cái hàm như findById này nó sẵn luôn rồi không cần khai báo đâu, chỉ việc '.' mà dùng thôi , khai báo nó còn sai thêm đấy
      void deleteByIdIn(List<Long> ids);
//      BuildingEntity findById(long id);
}
