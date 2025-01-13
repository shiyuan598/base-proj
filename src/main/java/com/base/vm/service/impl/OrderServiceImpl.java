package com.base.vm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VOrder;
import com.base.vm.mapper.OrderMapper;
import com.base.vm.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, VOrder>
        implements OrderService {
}
