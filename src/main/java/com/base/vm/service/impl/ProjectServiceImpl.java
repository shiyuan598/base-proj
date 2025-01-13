package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VProject;
import com.base.vm.mapper.ProjectMapper;
import com.base.vm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, VProject>
        implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<VProject> findAll() {
        return projectMapper.selectList(new QueryWrapper<VProject>());
    }
}
