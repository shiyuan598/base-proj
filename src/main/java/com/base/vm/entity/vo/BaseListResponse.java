package com.base.vm.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "列表响应对象")
public class BaseListResponse<T> extends BaseResponse<List<T>> {
}
