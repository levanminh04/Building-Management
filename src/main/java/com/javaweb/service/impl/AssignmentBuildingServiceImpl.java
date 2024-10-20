package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository  userRepository;
    @Override

    public void deleteByBuildingIdIn(List<Long> ids) {
        assignmentBuildingRepository.deleteByBuildingEntityIdIn(ids);
    }

    @Override
    public void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteByBuildingEntityId(buildingEntity.getId());  // 1 building id có thể được gán cho nhiều bản ghi của assignmentbuilding, nên ghi xóa thì nó sẽ xóa tất cả các bản ghi có buildingid ta truyền vào chứ không phải chỉ xóa 1 bản ghi

        List<Long> staffIds = assignmentBuildingDTO.getStaffs();   // đây là những thằng nhân viên được tích chọn
        for (Long it : staffIds) {
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuildingEntity(buildingEntity);
            UserEntity userEntity = userRepository.findById(it).get();
            assignmentBuildingEntity.setUserEntity(userEntity);
            assignmentBuildingRepository.save(assignmentBuildingEntity); // giao toà nhà
        }
    }
}
