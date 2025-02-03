package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Data;

@Data
public class CustomerSearchRequest extends AbstractDTO {
    private String fullname;
    private String phone;
    private String email;
    private Long staffid;
}
