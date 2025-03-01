package com.base.vm.entity.vo.vehicle;

import com.base.vm.entity.vo.BasePageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "车辆分页列表响应对象")
public class VehiclePageResponse extends BasePageResponse<VehicleListVO> {}
