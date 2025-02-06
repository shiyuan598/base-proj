package com.base.vm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.order.OrderListVO;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends BaseMapper<VOrder> {

    IPage<OrderListVO> getOrderPage(Page<?> page, @Param("query") QueryDTO queryDto);
}