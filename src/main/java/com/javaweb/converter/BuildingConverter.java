package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO){

        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        buildingEntity.setRentAreaEntities(rentAreaConverter.toRentAreaEntityList(buildingDTO, buildingEntity)); // chỉ cần set cái RentAreaEntity vào buildingEntity là hàm save nhờ có cascade sẽ tự save vào bảo rentArea luôn, không cần thêm thủ công như vers 1
        return buildingEntity;
    }
}
