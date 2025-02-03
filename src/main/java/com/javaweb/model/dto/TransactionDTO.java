package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO extends AbstractDTO{
    private String note;
    private Long customerId;
    private String code;
    private Long staffid;
}
