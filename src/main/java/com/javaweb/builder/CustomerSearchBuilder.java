package com.javaweb.builder;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CustomerSearchBuilder extends AbstractBuilder{
    private String fullname;
    private String phone;
    private String email;
    private Long staffid;
}
