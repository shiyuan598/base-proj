package com.base.vm.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVehicleDTO {
    @Schema(description = "车辆编号")
    private String vehicleNo;
    @Schema(description = "所属项目")
    private String project;
    @Schema(description = "状态，0:不可用，1:可用")
    private Integer state;
    @Schema(description = "地点")
    private String place;
    @Schema(description = "不可用原因")
    private String reason;
}
