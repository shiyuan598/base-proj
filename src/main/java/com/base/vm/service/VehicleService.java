package com.base.vm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.vehicle.VehicleDictVO;
import com.base.vm.entity.vo.vehicle.VehicleListVO;

import java.util.List;
import java.util.Map;

public interface VehicleService extends IService<VVehicle> {

    List<VehicleDictVO> getAvailableVehicles();

    IPage<Map<String, Object>> getVehiclePage(QueryDTO queryDto);
    IPage<VehicleListVO> getVehicleVOPage(QueryDTO queryDto);
}
