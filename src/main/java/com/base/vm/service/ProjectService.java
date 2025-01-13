package com.base.vm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VProject;

import java.util.List;

public interface ProjectService extends IService<VProject> {

    List<VProject> findAll();
}