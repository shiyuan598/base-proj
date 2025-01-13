package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VLoad;
import com.base.vm.mapper.LoadMapper;
import com.base.vm.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadServiceImpl extends ServiceImpl<LoadMapper, VLoad>
        implements LoadService {

    @Autowired
    private LoadMapper loadMapper;

    @Override
    public List<VLoad> findAll() {
        return loadMapper.selectList(new QueryWrapper<VLoad>());
    }
}