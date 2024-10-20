package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
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
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private BuildingService buildingService;

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
        responseDTO.setMessage("hello mother fucker");
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
        rentAreaService.deleteByBuildings(ids);                // muốn xóa building thì trước tiên phải xóa những thằng có khóa ngoại là buildingid trước đã
        assignmentBuildingService.deleteByBuildingIdIn(ids);   // rentArea và assignmentbuilding là 2 bảng có quan hệ với bảng building nên phải xóa trước
        buildingRepository.flush(); // Đẩy các thay đổi vào cơ sở dữ liệu: Khi bạn gọi flush(), nó sẽ phát sinh các lệnh SQL tương ứng (INSERT, UPDATE, DELETE) và gửi chúng đến cơ sở dữ liệu để thực hiện, nhưng không kết thúc giao dịch. Nghĩa là các thay đổi sẽ được "đẩy" vào cơ sở dữ liệu, nhưng vẫn có thể được rollback nếu giao dịch bị hủy sau đó. sử dụng để debug là chủ yếu, ở đây đang bị lỗi xóa building nên cho flush vào để check xem thằng rentarea và assignmentbuilding có quan hệ với building đã thực sự được xóa chưa
        //List<BuildingEntity> ds =  buildingRepository.findAll();
        buildingRepository.deleteByIdIn(ids);                  // building xóa cuối cùng
        
    }


    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        // Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));        // đoạn này không cần phải delete building như ở bên rentArea vì  update hay add new thì sẽ được quyết định bởi cái id mình truyen vào

        buildingRepository.save(buildingEntity);   // lưu building trước xong mới lưu những cái có quan hệ với bảng building
        buildingDTO.setId(buildingEntity.getId());   // không set id cho buildingDTO thì sau nhảy vào addRentArea bị lỗi đấy
        if(StringUtils.check(buildingDTO.getRentArea())){
            rentAreaService.addRentArea(buildingDTO);
        }
        return buildingDTO;
    }

    @Override
    public BuildingDTO findById(Long id) {
        return null;
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        return null;
    }
}
