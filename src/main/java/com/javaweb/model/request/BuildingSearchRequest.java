package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingSearchRequest extends AbstractDTO {
    private String name;
    private Long floorarea;
    private String district;
    private String ward;
    private String street;
    private Long numberofbasement;
    private String direction;
    private Long level;
    private Long minarea;
    private Long maxarea;
    private Long minprice;
    private Long maxprice;
    private String managername;
    private String managerphone;
    private Long staffid;
    private List<String> typecode;


}
