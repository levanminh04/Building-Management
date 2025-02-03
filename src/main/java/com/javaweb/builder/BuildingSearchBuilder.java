package com.javaweb.builder;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Getter
@SuperBuilder // Enables inheritance of the builder pattern, SuperBuilder đã bao gồm cả @Builder
public class BuildingSearchBuilder extends AbstractBuilder {
    private String name;
    private Long floorArea;
    private String ward;
    private String street;
    private String district;
    private Integer numberOfBasement;
    private List<String> typeCode;
    private String managerName;
    private String managerPhone;
    private Long minPrice;
    private Long maxPrice;
    private Long minArea;
    private Long maxArea;
    private Long staffId;
    private String direction;
    private Long level;


}

//    CÁCH XÂY DỰNG BUILDER THỦ CÔNG

//
//    private BuildingSearchBuilder(Builder builder) {
//        this.name = builder.name;
//        this.floorArea = builder. floorArea;
//        this.ward = builder.ward;
//        this.street = builder.street;
//        this.district = builder.district;
//        this.numberOfBasement = builder.numberOfBasement;
//        this.typeCode = builder.typeCode;
//        this.managerName = builder.managerName;
//        this.managerPhone = builder.managerPhone;
//        this.minPrice = builder.minPrice;
//        this.maxPrice = builder.maxPrice;
//        this.minArea = builder.minArea;
//        this.maxArea = builder.maxArea;
//        this.staffId = builder.staffId;
//        this.direction = builder.direction;
//        this.level = builder.level;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public Long getFloorArea() {
//        return floorArea;
//    }
//    public String getWard() {
//        return ward;
//    }
//    public String getStreet() {
//        return street;
//    }
//    public String getDistrict() {
//        return district;
//    }
//    public Integer getNumberOfBasement() {
//        return numberOfBasement;
//    }
//    public List<String> getTypeCode() {
//        return typeCode;
//    }
//    public String getManagerName() {
//        return managerName;
//    }
//    public String getManagerPhone() {
//        return managerPhone;
//    }
//    public Long getMinPrice() {
//        return minPrice;
//    }
//    public Long getMaxPrice() {
//        return maxPrice;
//    }
//    public Long getMinArea() {
//        return minArea;
//    }
//    public Long getMaxArea() {
//        return maxArea;
//    }
//    public Long getStaffId() {
//        return staffId;
//    }
//    public String getDirection() {
//        return direction;
//    }
//
//    public Long getLevel() {
//        return level;
//    }
//
//    public static class Builder{
//        private String name;
//        private Long floorArea;
//        private String ward;
//        private String street;
//        private String district;
//        private Integer numberOfBasement;
//        private List<String> typeCode = new ArrayList<>();
//        private String managerName;
//        private String managerPhone;
//        private Long minPrice;
//        private Long maxPrice;
//        private Long minArea;
//        private Long maxArea;
//        private Long staffId;
//        private String direction;
//        private Long level;
//
//        public Builder setName(String name) {
//            this.name = name;
//            return this;
//        }
//        public Builder setFloorArea(Long floorArea) {
//            this.floorArea = floorArea;
//            return this;
//        }
//        public Builder setWard(String ward) {
//            this.ward = ward;
//            return this;
//        }
//        public Builder setStreet(String street) {
//            this.street = street;
//            return this;
//        }
//        public Builder setDistrict(String district) {
//            this.district = district;
//            return this;
//        }
//        public Builder setNumberOfBasement(Integer numberOfBasement) {
//            this.numberOfBasement = numberOfBasement;
//            return this;
//        }
//        public Builder setTypeCode(List<String> typeCode) {
//            this.typeCode = typeCode;
//            return this;
//        }
//        public Builder setManagerName(String managerName) {
//            this.managerName = managerName;
//            return this;
//        }
//        public Builder setManagerPhone(String managerPhone) {
//            this.managerPhone = managerPhone;
//            return this;
//        }
//        public Builder setMinPrice(Long minPrice) {
//            this.minPrice = minPrice;
//            return this;
//        }
//        public Builder setMaxPrice(Long maxPrice) {
//            this.maxPrice = maxPrice;
//            return this;
//        }
//        public Builder setMinArea(Long minArea) {
//            this.minArea = minArea;
//            return this;
//        }
//        public Builder setMaxArea(Long maxArea) {
//            this.maxArea = maxArea;
//            return this;
//        }
//        public Builder setStaffId(Long staffId) {
//            this.staffId = staffId;
//            return this;
//        }
//        public Builder setDirection(String direction) {
//            this.direction = direction;
//            return this;
//        }
//        public Builder setLevel(Long level) {
//            this.level = level;
//            return this;
//        }
//        public BuildingSearchBuilder build() {
//            return new BuildingSearchBuilder(this);
//        }
//    }
//
//}


//         SỬ DỤNG BUILDER THỦ CÔNG


//BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()     // đây KHÔNG PHẢI là sử dụng một phương thức .builder() mà đây là trực tiếp gọi constructor của lớp Builder mà không cần định nghĩa thêm bất kỳ phương thức nào.
//        .setName(MapUtils.getObject(buildingSearchRequest.getName(), String.class))
//        .setFloorArea(MapUtils.getObject(buildingSearchRequest.getFloorarea(), Long.class))
//        .setDistrict(MapUtils.getObject(buildingSearchRequest.getDistrict(), String.class))
//        .setManagerName(MapUtils.getObject(buildingSearchRequest.getManagername(), String.class))
//        .setManagerPhone(MapUtils.getObject(buildingSearchRequest.getManagerphone(), String.class))
//        .setMaxArea(MapUtils.getObject(buildingSearchRequest.getMaxarea(), Long.class))
//        .setMinArea(MapUtils.getObject(buildingSearchRequest.getMinarea(), Long.class))
//        .setMaxPrice(MapUtils.getObject(buildingSearchRequest.getMaxprice(), Long.class))
//        .setMinPrice(MapUtils.getObject(buildingSearchRequest.getMinprice(), Long.class))
//        .setNumberOfBasement(MapUtils.getObject(buildingSearchRequest.getNumberofbasement(), Integer.class))
//        .setStaffId(MapUtils.getObject(buildingSearchRequest.getStaffid(), Long.class))
//        .setStreet(MapUtils.getObject(buildingSearchRequest.getStreet(), String.class))
//        .setWard(MapUtils.getObject(buildingSearchRequest.getWard(), String.class))
//        .setTypeCode(typecode)
//        .build();
//        return buildingSearchBuilder;




//public static <T> T getObject(Object item, Class<T> tClass) {
//    if(item != null) {
//        if(tClass.getTypeName().equals("java.lang.Long")) {
//            item = item != "" ? Long.valueOf(item.toString()) : null;   // cách này cũng không hay lắm, nếu muốn kiểm tra thm ví dụ xem giá trị có hợp lệ hay không > hơn 0 ....
//        }
//        else if(tClass.getTypeName().equals("java.lang.Integer")) {
//            item = item != "" ? Integer.valueOf(item.toString()) : null;
//        }
//        else if(tClass.getTypeName().equals("java.lang.String")) {
//            item = item.toString();
//        }
//        return tClass.cast(item);
//    }
//    return null;
//}