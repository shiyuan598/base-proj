package com.base.vm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VOrderState;
import com.base.vm.mapper.OrderStateMapper;
import com.base.vm.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStateServiceImpl extends ServiceImpl<OrderStateMapper, VOrderState>
        implements OrderStateService {

    @Autowired
    private OrderStateMapper orderStateMapper;

    @Override
    public List<VOrderState> findAll() {
        return orderStateMapper.selectList(null);
    }
}

