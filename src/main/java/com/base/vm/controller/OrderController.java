package com.base.vm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.vo.order.OrderPageResponse;
import com.base.vm.service.OrderService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController extends ResultUtil {
    private final OrderService orderService;

    @Operation(summary = "订单分页列表")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "查询成功",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderPageResponse.class)))
    public ResponseEntity<Object> getOrders(@Parameter(description = "模糊搜索关键字")
                                                @RequestParam(required = false) String blurry,

                                            @Parameter(description = "当前页码", example = "1")
                                                @RequestParam(defaultValue = "1") long currentPage,

                                            @Parameter(description = "每页条数", example = "10")
                                                @RequestParam(defaultValue = "10") long pageSize,

                                            @Parameter(description = "排序字段")
                                                @RequestParam(required = false) String sort,

                                            @Parameter(description = "排序方向 (asc/desc)")
                                                @RequestParam(required = false) String order,

                                            @Parameter(description = "预订人")
                                                @RequestParam(required = false) String subscriber) {
        try {
            LambdaQueryWrapper<VOrder> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(blurry)) {
                queryWrapper.like(VOrder::getVehicleNo, blurry)
                        .or()
                        .like(VOrder::getModule, blurry)
                        .or()
                        .like(VOrder::getSubscriber, blurry);
            }
            // 处理排序
            if (StringUtils.isNotBlank(sort)) {
                boolean isAsc = StringUtils.isBlank(order) ||
                        "asc".equalsIgnoreCase(order);

                // 根据不同字段排序
                switch (sort.toLowerCase()) {
                    case "project":
                        queryWrapper.orderBy(true, isAsc, VOrder::getProject);
                        break;
                    case "module":
                        queryWrapper.orderBy(true, isAsc, VOrder::getModule);
                        break;
                    case "vehicleno":
                        queryWrapper.orderBy(true, isAsc, VOrder::getVehicleNo);
                        break;
                    case "starttime":
                        queryWrapper.orderBy(true, isAsc, VOrder::getStarttime);
                        break;
                    case "state":
                        queryWrapper.orderBy(true, isAsc, VOrder::getState);
                        break;
                    default:
                        // 默认按id降序
                        queryWrapper.orderByDesc(VOrder::getId);
                }
            } else {
                // 默认按id降序
                queryWrapper.orderByDesc(VOrder::getId);
            }

            // 特定用户的订单
            if (subscriber != null) {
                queryWrapper.eq(VOrder::getSubscriber, subscriber);
            }

            return success(true, orderService.page(new Page<>(currentPage, pageSize), queryWrapper));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "订单详情")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "查询成功",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VOrder.class)))
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        try {
            return success(true, orderService.getById(id));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "增加订单")
    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody VOrder order) {
        try {
            return success(true, orderService.save(order));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "更新订单")
    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody VOrder order) {
        try {
            return success(true, orderService.save(order));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
