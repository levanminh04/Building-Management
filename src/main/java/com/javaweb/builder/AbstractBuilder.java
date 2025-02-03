package com.javaweb.builder;


import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder // Lombok annotation to enable inheritance in builders
public abstract class AbstractBuilder {
    private Long id;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String searchValue;
}