package com.base.vm.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDTO {
    private String blurry;

    private long currentPage = 1;

    private long pageSize = 10;

    private String sort;

    private String order;
}
