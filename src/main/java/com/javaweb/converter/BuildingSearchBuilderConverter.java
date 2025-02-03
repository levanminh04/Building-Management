package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class BuildingSearchBuilderConverter {

    private static final long DEFAULT_MIN_AREA = 1L;
    private static final long DEFAULT_MAX_AREA = Long.MAX_VALUE;
    private static final long DEFAULT_MIN_PRICE = 0L;
    private static final int DEFAULT_MIN_BASEMENT = 0;
    private static final int DEFAULT_MAX_BASEMENT = 100;

    /**
     * Chuyển đổi BuildingSearchRequest và danh sách typeCode thành đối tượng BuildingSearchBuilder.
     *
     * @param request  Yêu cầu tìm kiếm chứa các tiêu chí lọc.
     * @param typeCode Danh sách mã loại hình xây dựng.
     * @return Đối tượng BuildingSearchBuilder đã được khởi tạo với các giá trị hợp lệ.
     */
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest request, List<String> typeCode) {
        // Parse và validate diện tích tối thiểu và tối đa
        Long minArea = parseLong(request.getMinarea(), DEFAULT_MIN_AREA, DEFAULT_MAX_AREA);
        Long maxArea = parseLong(request.getMaxarea(), DEFAULT_MIN_AREA, DEFAULT_MAX_AREA);
        ensureValidRange(minArea, maxArea, "Min area", "Max area");

        // Parse và validate giá tối thiểu và tối đa
        Long minPrice = parseLong(request.getMinprice(), DEFAULT_MIN_PRICE, DEFAULT_MAX_AREA);
        Long maxPrice = parseLong(request.getMaxprice(), DEFAULT_MIN_PRICE, DEFAULT_MAX_AREA);
        ensureValidRange(minPrice, maxPrice, "Min price", "Max price");

        // Khi sử dụng .builder() trong Lombok (với @Builder hoặc @SuperBuilder), với @SuperBuilder thì .builder() khi này là 1 phương thức được lombok cung cấp chứ không phải là 1 constructor, còn khi sử dụng từ khóa new thì .Builder() là 1 constructor
        return BuildingSearchBuilder.builder()
                .name(normalizeString(request.getName())) // Xử lý và lấy giá trị tên
                .floorArea(parseLong(request.getFloorarea(), DEFAULT_MIN_AREA, DEFAULT_MAX_AREA)) // Parse diện tích sàn
                .district(normalizeString(request.getDistrict())) // Xử lý giá trị quận/huyện
                .managerName(normalizeString(request.getManagername())) // Xử lý giá trị tên quản lý
                .managerPhone(Optional.ofNullable(request.getManagerphone())
                        .map(phone -> phone.isEmpty() ? null : phone) // Chuyển chuỗi rỗng thành null
                        .filter(phone -> phone.matches("\\d{10,15}")) // Kiểm tra định dạng
                        .orElse(null)) // Validate số điện thoại quản lý
                .maxArea(maxArea)
                .minArea(minArea)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .numberOfBasement(parseInteger(request.getNumberofbasement(), DEFAULT_MIN_BASEMENT, DEFAULT_MAX_BASEMENT)) // Parse số tầng hầm
                .staffId(parseLong(request.getStaffid(), 0L, DEFAULT_MAX_AREA)) // Parse ID nhân viên
                .street(normalizeString(request.getStreet())) // Xử lý tên đường
                .ward(normalizeString(request.getWard())) // Xử lý phường/xã
                .typeCode(Optional.ofNullable(typeCode).orElse(List.of())) // Nếu typeCode null, sử dụng danh sách rỗng
                .build();
    }

    /**
     * Chuyển đổi giá trị thành kiểu Long và validate trong khoảng giá trị cho phép.
     *
     * @param value    Giá trị đầu vào cần chuyển đổi.
     * @param minValue Giá trị nhỏ nhất được chấp nhận.
     * @param maxValue Giá trị lớn nhất được chấp nhận.
     * @return Giá trị Long hợp lệ hoặc null nếu đầu vào không hợp lệ.
     */
    private Long parseLong(Object value, Long minValue, Long maxValue) {
        return Optional.ofNullable(value)
                .filter(this::isNotBlank) // Kiểm tra giá trị không rỗng
                .map(this::toLong) // Chuyển đổi giá trị sang Long
                .filter(longValue -> isWithinRange(longValue, minValue, maxValue)) // Validate khoảng giá trị
                .orElse(null);
    }

    /**
     * Chuyển đổi giá trị Object thành Long.
     *
     * @param value Giá trị cần chuyển đổi.
     * @return Giá trị Long hợp lệ.
     * @throws IllegalArgumentException Nếu giá trị không thể chuyển đổi.
     */
    private Long toLong(Object value) {
        try {
            return Long.valueOf(value.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Long value: " + value, e);
        }
    }

    /**
     * Kiểm tra giá trị Long có nằm trong khoảng cho phép.
     *
     * @param value    Giá trị cần kiểm tra.
     * @param minValue Giá trị nhỏ nhất.
     * @param maxValue Giá trị lớn nhất.
     * @return true nếu giá trị hợp lệ.
     */
    private boolean isWithinRange(Long value, Long minValue, Long maxValue) {
        return value >= minValue && value <= maxValue;
    }

    /**
     * Đảm bảo giá trị min không lớn hơn max.
     *
     * @param minValue Giá trị nhỏ nhất.
     * @param maxValue Giá trị lớn nhất.
     * @param minLabel Tên trường min (dùng trong thông báo lỗi).
     * @param maxLabel Tên trường max (dùng trong thông báo lỗi).
     */
    private void ensureValidRange(Long minValue, Long maxValue, String minLabel, String maxLabel) {
        if (minValue != null && maxValue != null && minValue > maxValue) {
            throw new IllegalArgumentException(String.format("%s cannot be greater than %s", minLabel, maxLabel));
        }
    }

    /**
     * Xử lý chuỗi, trả về giá trị không rỗng hoặc null nếu chuỗi không hợp lệ.
     *
     * @param value Chuỗi đầu vào.
     * @return Chuỗi đã được làm sạch hoặc null.
     */
    private String normalizeString(String value) {
        return Optional.ofNullable(value)
                .filter(this::isNotBlank)
                .orElse(null);
    }

    /**
     * Kiểm tra giá trị không rỗng và không phải chuỗi trắng.
     *
     * @param value Giá trị cần kiểm tra.
     * @return true nếu giá trị hợp lệ.
     */
    private boolean isNotBlank(Object value) {
        return value != null && StringUtils.isNotBlank(value.toString());
    }


    /**
     * Chuyển đổi giá trị thành kiểu Integer và validate trong khoảng giá trị cho phép.
     *
     * @param value    Giá trị đầu vào cần chuyển đổi.
     * @param minValue Giá trị nhỏ nhất được chấp nhận.
     * @param maxValue Giá trị lớn nhất được chấp nhận.
     * @return Giá trị Integer hợp lệ hoặc null nếu đầu vào không hợp lệ.
     */
    private Integer parseInteger(Object value, Integer minValue, Integer maxValue) {
        return Optional.ofNullable(value)
                .filter(this::isNotBlank) // Kiểm tra giá trị không rỗng
                .map(this::toInteger) // Chuyển đổi giá trị sang Integer
                .filter(intValue -> intValue >= minValue && intValue <= maxValue) // Validate khoảng giá trị
                .orElse(null);
    }

    /**
     * Chuyển đổi giá trị Object thành Integer.
     *
     * @param value Giá trị cần chuyển đổi.
     * @return Giá trị Integer hợp lệ.
     * @throws IllegalArgumentException Nếu giá trị không thể chuyển đổi.
     */
    private Integer toInteger(Object value) {
        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Integer value: " + value, e);
        }
    }
}


//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//// Improved version of MapUtils, using Optional for better null safety
//public class MapUtils {
//
//    public static <T> T getObject(Object item, Class<T> tClass) {
//        return Optional.ofNullable(item)
//                .filter(obj -> StringUtils.isNotBlank(obj.toString()))
//                .map(obj -> {
//                    try {
//                        if (tClass == Long.class) {
//                            Long value = Long.valueOf(obj.toString());
//                            if (value > 0) return tClass.cast(value);
//                        } else if (tClass == Integer.class) {
//                            Integer value = Integer.valueOf(obj.toString());
//                            if (value > 0) return tClass.cast(value);
//                        } else if (tClass == String.class) {
//                            return tClass.cast(obj.toString());
//                        }
//                    } catch (NumberFormatException e) {
//                        throw new IllegalArgumentException("Invalid value for type: " + tClass.getName());
//                    }
//                    return null;
//                })
//                .orElse(null);
//    }
//}
//
//@Component
//public class BuildingSearchBuilderConverter {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest, List<String> typeCode) {
//        return new BuildingSearchBuilder.Builder()
//                .setName(Optional.ofNullable(buildingSearchRequest.getName()).orElse(null))
//                .setFloorArea(parseLong(buildingSearchRequest.getFloorarea()))
//                .setDistrict(Optional.ofNullable(buildingSearchRequest.getDistrict()).orElse(null))
//                .setManagerName(Optional.ofNullable(buildingSearchRequest.getManagername()).orElse(null))
//                .setManagerPhone(Optional.ofNullable(buildingSearchRequest.getManagerphone()).orElse(null))
//                .setMaxArea(parseLong(buildingSearchRequest.getMaxarea()))
//                .setMinArea(parseLong(buildingSearchRequest.getMinarea()))
//                .setMaxPrice(parseLong(buildingSearchRequest.getMaxprice()))
//                .setMinPrice(parseLong(buildingSearchRequest.getMinprice()))
//                .setNumberOfBasement(parseInteger(buildingSearchRequest.getNumberofbasement()))
//                .setStaffId(parseLong(buildingSearchRequest.getStaffid()))
//                .setStreet(Optional.ofNullable(buildingSearchRequest.getStreet()).orElse(null))
//                .setWard(Optional.ofNullable(buildingSearchRequest.getWard()).orElse(null))
//                .setTypeCode(Optional.ofNullable(typeCode).orElse(List.of()))
//                .build();
//    }
//
//    private Long parseLong(Object value) {
//        return Optional.ofNullable(value)
//                .filter(val -> StringUtils.isNotBlank(val.toString()))
//                .map(val -> {
//                    try {
//                        return Long.valueOf(val.toString());
//                    } catch (NumberFormatException e) {
//                        throw new IllegalArgumentException("Invalid Long value: " + val);
//                    }
//                })
//                .orElse(null);
//    }
//
//    private Integer parseInteger(Object value) {
//        return Optional.ofNullable(value)
//                .filter(val -> StringUtils.isNotBlank(val.toString()))
//                .map(val -> {
//                    try {
//                        return Integer.valueOf(val.toString());
//                    } catch (NumberFormatException e) {
//                        throw new IllegalArgumentException("Invalid Integer value: " + val);
//                    }
//                })
//                .orElse(null);
//    }
//}
//
//// Cleaner BuildingSearchBuilder using Lombok for brevity
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//@Builder
//public class BuildingSearchBuilder {
//    private String name;
//    private Long floorArea;
//    private String ward;
//    private String street;
//    private String district;
//    private Integer numberOfBasement;
//    private List<String> typeCode;
//    private String managerName;
//    private String managerPhone;
//    private Long minPrice;
//    private Long maxPrice;
//    private Long minArea;
//    private Long maxArea;
//    private Long staffId;
//    private String direction;
//    private Long level;
//}
