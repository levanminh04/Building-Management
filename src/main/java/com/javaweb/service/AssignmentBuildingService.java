package com.javaweb.service;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;

import java.util.List;

public interface AssignmentBuildingService {
    void deleteByBuildingIdIn(List<Long> ids);
    void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO);
}
