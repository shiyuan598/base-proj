package com.base.vm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.order.OrderListVO;

public interface OrderService extends IService<VOrder> {
    IPage<OrderListVO> getOrderPage(QueryDTO queryDto);
}