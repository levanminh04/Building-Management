package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Data;

@Data
public class CustomerSearchResponse extends AbstractDTO {
    private Long id;
    private String fullname;
    private String phone;
    private String email;
    private String demand;
    private String status;
}
