package com.base.vm.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "车辆字典")
public class VehicleDictVO {
    @Schema(description = "车辆ID")
    private Integer id;
    @Schema(description = "项目名称")
    private String project;
    @Schema(description = "车辆编号")
    private String vehicleNo;
    @Schema(description = "车辆状态")
    private Integer state;
}
