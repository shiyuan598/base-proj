package com.base.vm.entity.vo;

import com.base.vm.entity.VOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "车辆分页列表响应对象")
public class OrderPageResponse extends BasePageResponse<VOrder> {}
