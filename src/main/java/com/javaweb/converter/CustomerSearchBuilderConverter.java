package com.javaweb.converter;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.model.request.CustomerSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerSearchBuilderConverter {
    public CustomerSearchBuilder toCustomerSearchBuilder(CustomerSearchRequest request) {

        return CustomerSearchBuilder.builder()
                .fullname(normalizeString(request.getFullname()))
                .phone(Optional.ofNullable(request.getPhone())
                        .map(phone -> phone.isEmpty() ? null : phone) // Chuyển chuỗi rỗng thành null
                        .filter(phone -> phone.matches("\\d{10,15}")) // Kiểm tra định dạng
                        .orElse(null))
                .email(normalizeString(request.getEmail()))
                .staffid(validateStaffId(request.getStaffid()))
                .build();
    }


    private String normalizeString(String value) {
        return Optional.ofNullable(value)
                .map(String::trim)
                .filter(this::isNotBlank)// isNotBlank method of CustomerSearchBuilderConverter class
                .orElse(null);
    }
    // Optional.ofNullable(value) gọi được gọi trước trim(). trim() chỉ được gọi khi value không phải là null. Nếu value là null, Optional sẽ bỏ qua toàn bộ chuỗi xử lý và trả về null.

    private boolean isNotBlank(Object value) {
        return value != null && StringUtils.isNotBlank(value.toString());
    }

    private Long validateStaffId(Long staffid) {
        if (staffid == null || staffid <= 0) { // Staff ID phải lớn hơn 0
            return null; // Nếu staffid không hợp lệ, trả về null
        }
        return staffid; // Trả về staffid hợp lệ
    }

}
