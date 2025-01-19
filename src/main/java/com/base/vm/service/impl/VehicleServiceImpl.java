package com.base.vm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.VehicleDictVO;
import com.base.vm.entity.vo.VehicleListVO;
import com.base.vm.mapper.VehicleMapper;
import com.base.vm.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, VVehicle>
        implements VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    public List<VehicleListVO> getAvailableVehicles() {
        try {
            return vehicleMapper.selectAvailableVehicles();
        } catch (Exception e) {
            // 处理异常，比如记录日志并返回一个空列表
            log.error("Error finding available vehicles", e);
            return Collections.emptyList();
        }
    }

    @Override
    public IPage<Map<String, Object>> getVehiclePage(QueryDTO queryDto) {
        try {
            Page<Map<String, Object>> page = new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize());
            return vehicleMapper.getVehiclePage(page, queryDto);
        } catch (Exception e) {
            // 处理异常，比如记录日志并返回一个空列表
            log.error("Error finding available vehicles", e);
            return new Page<>();
        }
    }

    @Override
    public IPage<VehicleDictVO> getVehicleVOPage(QueryDTO queryDto) {
        try {
            Page<VehicleDictVO> page = new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize());
            return vehicleMapper.getVehicleVOPage(page, queryDto);
        } catch (Exception e) {
            return new Page<>();
        }
    }
}
