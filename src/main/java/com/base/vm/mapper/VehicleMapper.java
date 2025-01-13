package com.base.vm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.VehicleDetailVO;
import com.base.vm.entity.vo.VehicleListVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleMapper extends BaseMapper<VVehicle> {
    VVehicle selectVehicleDtoById(@Param("id") Integer id);
    List<VehicleListVO> selectAvailableVehicles();
    @MapKey("id")
    IPage<Map<String, Object>> getVehiclePage(Page<?> page, @Param("query") QueryDTO queryDto);
    IPage<VehicleDetailVO> getVehicleVOPage(Page<?> page, @Param("query") QueryDTO queryDto);
}