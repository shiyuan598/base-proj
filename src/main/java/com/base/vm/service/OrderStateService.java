package com.base.vm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VOrderState;

import java.util.List;

public interface OrderStateService extends IService<VOrderState> {

    List<VOrderState> findAll();
}