package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {


    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository  userRepository;


    @Override
    public void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {

        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List <UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setUsers(staffs);
        buildingRepository.save(buildingEntity);
    }
}
