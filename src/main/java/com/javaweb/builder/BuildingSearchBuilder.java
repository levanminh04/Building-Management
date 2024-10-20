package com.javaweb.builder;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private String ward;
    private String street;
    private String district;
    private Integer numberOfBasement;
    private List<String> typeCode = new ArrayList<>();
    private String managerName;
    private String managerPhone;
    private Long minPrice;
    private Long maxPrice;
    private Long minArea;
    private Long maxArea;
    private Long staffId;
    private String direction;
    private Long level;



    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorArea = builder. floorArea;
        this.ward = builder.ward;
        this.street = builder. street;
        this.district = builder.district;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
        this.minArea = builder.minArea;
        this.maxArea = builder.maxArea;
        this.staffId = builder.staffId;
        this.direction = builder.direction;
        this.level = builder.level;
    }

    public String getName() {
        return name;
    }
    public Long getFloorArea() {
        return floorArea;
    }
    public String getWard() {
        return ward;
    }
    public String getStreet() {
        return street;
    }
    public String getDistrict() {
        return district;
    }
    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }
    public List<String> getTypeCode() {
        return typeCode;
    }
    public String getManagerName() {
        return managerName;
    }
    public String getManagerPhone() {
        return managerPhone;
    }
    public Long getMinPrice() {
        return minPrice;
    }
    public Long getMaxPrice() {
        return maxPrice;
    }
    public Long getMinArea() {
        return minArea;
    }
    public Long getMaxArea() {
        return maxArea;
    }
    public Long getStaffId() {
        return staffId;
    }
    public String getDirection() {
        return direction;
    }

    public Long getLevel() {
        return level;
    }

    public static class Builder{
        private String name;
        private Long floorArea;
        private String ward;
        private String street;
        private String district;
        private Integer numberOfBasement;
        private List<String> typeCode = new ArrayList<>();
        private String managerName;
        private String managerPhone;
        private Long minPrice;
        private Long maxPrice;
        private Long minArea;
        private Long maxArea;
        private Long staffId;
        private String direction;
        private Long level;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }
        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }
        public Builder setMinPrice(Long minPrice) {
            this.minPrice = minPrice;
            return this;
        }
        public Builder setMaxPrice(Long maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }
        public Builder setMinArea(Long minArea) {
            this.minArea = minArea;
            return this;
        }
        public Builder setMaxArea(Long maxArea) {
            this.maxArea = maxArea;
            return this;
        }
        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }
        public Builder setLevel(Long level) {
            this.level = level;
            return this;
        }
        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }

}