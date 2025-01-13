package com.base.vm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VLoad;

import java.util.List;

public interface LoadService extends IService<VLoad> {

    List<VLoad> findAll();
}