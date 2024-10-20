package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.ArrayList;
import java.util.List;

public interface BuildingService {
    ResponseDTO loadStaffs(Long id);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    public void deleteBuildings( List<Long> ids);
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);
    public BuildingDTO findById(Long id);
    ResponseDTO listStaffs(Long buildingId);

}
