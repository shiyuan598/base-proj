package com.base.vm.entity.vo;

import com.base.vm.entity.VUser;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "车辆分页列表响应对象")
public class UserPageResponse extends BasePageResponse<VUser> {}
