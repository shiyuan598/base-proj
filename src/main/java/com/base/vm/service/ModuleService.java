package com.base.vm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VModule;

import java.util.List;

public interface ModuleService extends IService<VModule> {

    List<VModule> findAll();
}