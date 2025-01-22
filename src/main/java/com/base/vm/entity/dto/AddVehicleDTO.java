package com.base.vm.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVehicleDTO {
    @NotBlank
    @Schema(description = "车辆编号")
    private String vehicleNo;
    @NotNull
    @Schema(description = "所属项目")
    private String project;
    @NotNull
    @Schema(description = "状态，0:不可用，1:可用")
    private Integer state;
    @NotBlank
    @Schema(description = "地点")
    private String place;
    @Schema(description = "不可用原因")
    private String reason;
}
