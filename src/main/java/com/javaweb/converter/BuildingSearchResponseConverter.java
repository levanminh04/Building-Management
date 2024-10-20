package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        List<RentAreaEntity> rentAreaEntity = buildingEntity.getRentAreaEntities();
        String areaResult = rentAreaEntity.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        String districtName = "";

        Map<String, String> mp = districtCode.type();

        if(buildingEntity.getDistrict() != null && buildingEntity.getDistrict().length() > 0) {
            districtName = mp.get(buildingEntity.getDistrict());
        }

        buildingSearchResponse.setAddress(buildingEntity.getStreet() + " " + buildingEntity.getWard() + " " + districtName);
        buildingSearchResponse.setRentArea(areaResult);
        return buildingSearchResponse;
    }
}
