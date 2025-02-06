package com.base.vm.entity.vo.order;

import com.base.vm.entity.vo.BasePageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "订单分页列表响应对象")
public class OrderPageResponse extends BasePageResponse<OrderListVO> {}
