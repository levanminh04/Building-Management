package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface BuildingService {
    ResponseDTO loadStaffs(Long id);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    void deleteBuildings( List<Long> ids);
    void addOrUpdateBuilding(BuildingDTO buildingDTO) throws IOException;
    BuildingDTO findById(Long id);
    ResponseDTO listStaffs(Long buildingId);
    List<BuildingEntity> getAllBuildings();
    BuildingEntity getBuildingById(Long id);
}
