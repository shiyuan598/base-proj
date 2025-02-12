package com.base.vm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.vm.entity.VUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<VUser> {
    VUser findByUsername(@Param("query") String username);
}
