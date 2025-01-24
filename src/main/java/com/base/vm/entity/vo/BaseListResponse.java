package com.base.vm.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "列表响应对象")
public class BaseListResponse<T> extends BaseResponse<List<T>> {
}
