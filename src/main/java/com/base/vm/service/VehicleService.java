package com.base.vm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.dto.VehicleDTO;
import com.base.vm.entity.vo.VehicleDictVO;
import com.base.vm.entity.vo.VehicleListVO;

import java.util.List;
import java.util.Map;

public interface VehicleService extends IService<VVehicle> {

    List<VehicleListVO> getAvailableVehicles();

    IPage<Map<String, Object>> getVehiclePage(QueryDTO queryDto);
    IPage<VehicleDictVO> getVehicleVOPage(QueryDTO queryDto);
}
