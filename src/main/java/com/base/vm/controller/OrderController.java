package com.base.vm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.dto.OrderQueryDTO;
import com.base.vm.service.OrderService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<Object> getOrders(OrderQueryDTO queryDto) {
        try {
            LambdaQueryWrapper<VOrder> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(queryDto.getBlurry())) {
                queryWrapper.like(VOrder::getVehicleno, queryDto.getBlurry())
                        .or()
                        .like(VOrder::getModule, queryDto.getBlurry())
                        .or()
                        .like(VOrder::getSubscriber, queryDto.getBlurry());
            }
            // 处理排序
            if (StringUtils.isNotBlank(queryDto.getSort())) {
                boolean isAsc = StringUtils.isBlank(queryDto.getOrder()) ||
                        "asc".equalsIgnoreCase(queryDto.getOrder());

                // 根据不同字段排序
                switch (queryDto.getSort().toLowerCase()) {
                    case "project":
                        queryWrapper.orderBy(true, isAsc, VOrder::getProject);
                        break;
                    case "module":
                        queryWrapper.orderBy(true, isAsc, VOrder::getModule);
                        break;
                    case "vehicleno":
                        queryWrapper.orderBy(true, isAsc, VOrder::getVehicleno);
                        break;
                    case "starttime":
                        queryWrapper.orderBy(true, isAsc, VOrder::getStarttime);
                        break;
                    case "state":
                        queryWrapper.orderBy(true, isAsc, VOrder::getState);
                        break;
                    default:
                        // 默认按创建时间降序
                        queryWrapper.orderByDesc(VOrder::getId);
                }
            } else {
                // 默认按id降序
                queryWrapper.orderByDesc(VOrder::getId);
            }

            // 特定用户的订单
            if (queryDto.getSubscriber() != null) {
                queryWrapper.eq(VOrder::getSubscriber, queryDto.getSubscriber());
            }

            return success(true, orderService.page(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize()), queryWrapper));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "订单详情")
    @GetMapping("/{id}")
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
