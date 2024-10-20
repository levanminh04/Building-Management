package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {


    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest, List<String> typecode) {

        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(buildingSearchRequest.getName(), String.class))
                .setFloorArea(MapUtils.getObject(buildingSearchRequest.getFloorarea(), Long.class))
                .setDistrict(MapUtils.getObject(buildingSearchRequest.getDistrict(), String.class))
                .setManagerName(MapUtils.getObject(buildingSearchRequest.getManagername(), String.class))
                .setManagerPhone(MapUtils.getObject(buildingSearchRequest.getManagerphone(), String.class))
                .setMaxArea(MapUtils.getObject(buildingSearchRequest.getMaxarea(), Long.class))
                .setMinArea(MapUtils.getObject(buildingSearchRequest.getMinarea(), Long.class))
                .setMaxPrice(MapUtils.getObject(buildingSearchRequest.getMaxprice(), Long.class))
                .setMinPrice(MapUtils.getObject(buildingSearchRequest.getMinprice(), Long.class))
                .setNumberOfBasement(MapUtils.getObject(buildingSearchRequest.getNumberofbasement(), Integer.class))
                .setStaffId(MapUtils.getObject(buildingSearchRequest.getStaffid(), Long.class))
                .setStreet(MapUtils.getObject(buildingSearchRequest.getStreet(), String.class))
                .setWard(MapUtils.getObject(buildingSearchRequest.getWard(), String.class))
                .setTypeCode(typecode)
                .build();
        return buildingSearchBuilder;
    }
}
