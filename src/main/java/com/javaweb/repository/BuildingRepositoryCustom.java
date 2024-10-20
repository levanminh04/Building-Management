package com.javaweb.repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;



public interface BuildingRepositoryCustom {
    public List <BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
