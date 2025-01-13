package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VModule;
import com.base.vm.mapper.ModuleMapper;
import com.base.vm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, VModule>
        implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<VModule> findAll() {
        return moduleMapper.selectList(new QueryWrapper<VModule>());
    }
}