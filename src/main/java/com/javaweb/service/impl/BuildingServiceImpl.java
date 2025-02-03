package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.BuildingRepositoryCustom;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.RentAreaRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public ResponseDTO loadStaffs(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List <UserEntity> staffList = buildingEntity.getUsers(); // chọn ra những thằng đã được giao tòa nhà để khi đổ lên giao diện thì đánh dấu checked
        List <UserEntity> allStaff = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();

        for(UserEntity staff : allStaff){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());

            if(staffList.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("danh sách nhân viên");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<String> typeCode = buildingSearchRequest.getTypecode();
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingSearchResponse> res = new ArrayList<>();

        for (BuildingEntity item : buildingEntities)
        {
            BuildingSearchResponse building = buildingSearchResponseConverter.toBuildingSearchResponse(item);
            res.add(building);
        }
        return res;
    }
    @Transactional
    @Override
    public void deleteBuildings( List<Long> ids) {
//        rentAreaService.deleteByBuildings(ids);
//        assignmentBuildingService.deleteByBuildingIdIn(ids);             // CÓ CASCADE THÌ KHÔNG CẦN BƯỚC NÀY NỮA
//        buildingRepository.flush();
        buildingRepository.deleteByIdIn(ids);

    }


    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) throws IOException {
        buildingConverter.toBuildingEntity(buildingDTO); // Không cần gọi save lần nữa
    }


    @Override
    public BuildingDTO findById(Long id) {
        return null;
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        return null;
    }

    @Override
    public List<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public BuildingEntity getBuildingById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }


}
