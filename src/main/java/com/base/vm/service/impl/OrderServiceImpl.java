package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.order.OrderListVO;
import com.base.vm.mapper.OrderMapper;
import com.base.vm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, VOrder>
        implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<OrderListVO> getOrderPage(QueryDTO queryDto) {
        try {
            Page<OrderListVO> page = new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize());
            return orderMapper.getOrderPage(page, queryDto);
        } catch (Exception e) {
            return new Page<>();
        }
    }
}
