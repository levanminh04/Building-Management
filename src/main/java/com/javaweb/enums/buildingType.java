package com.javaweb.enums;


import java.util.*;
// enum thường dùng với các dữ liệu hiếm khi bị thay đổi
public enum  buildingType {
    TANG_TRET ("Tầng Trệt "),
    NGUYEN_CAN ("Nguyên Căn "),
    NOI_THAT ("Nội Thất ");

    private final String name;

    buildingType(String name) {
        this.name = name;
    }

    public String getCode() {
        return name;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(buildingType item : buildingType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
    // Phương thức tìm giá trị enum theo code (e.g., "TANG_TRET" -> "Tầng Trệt")
    public static String fromCode(String code) {
        for (buildingType item : buildingType.values()) {
            if (item.name().equalsIgnoreCase(code)) { // So sánh tên enum
                return item.getCode();
            }
        }
        return "Không xác định"; // Trả về giá trị mặc định nếu không tìm thấy
    }
}
