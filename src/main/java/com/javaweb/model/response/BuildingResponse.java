package com.javaweb.model.response;

import lombok.Data;

import java.util.List;

@Data
public class BuildingResponse {
    private Long id;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String rentpricedescription;
    private String type;
    private String note;
    private Long floorarea;
    private String managerphone;
    private String managername;
    private Integer numberofbasement;
    private List<String> image_url;
    private String avt;
}
