package com.base.vm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.vehicle.VehicleDictVO;
import com.base.vm.entity.vo.vehicle.VehicleListVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleMapper extends BaseMapper<VVehicle> {
    VVehicle selectVehicleDtoById(@Param("id") Integer id);
    List<VehicleDictVO> selectAvailableVehicles();
    @MapKey("id") // 使用resultType="map"时需要设置MapKey
    IPage<Map<String, Object>> getVehiclePage(Page<?> page, @Param("query") QueryDTO queryDto);
    IPage<VehicleListVO> getVehicleVOPage(Page<?> page, @Param("query") QueryDTO queryDto);
}