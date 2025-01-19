package com.base.vm.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private Integer id;
    private String vehicleNo;
    private String project;
    private Integer state;
    private String stateName;
    private String place;
    private String reason;
}
