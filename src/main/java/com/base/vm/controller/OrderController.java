package com.base.vm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VOrder;
import com.base.vm.entity.dto.OrderQueryDTO;
import com.base.vm.entity.vo.order.OrderListVO;
import com.base.vm.entity.vo.order.OrderPageResponse;
import com.base.vm.service.OrderService;
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
            OrderQueryDTO queryDto = new OrderQueryDTO();
            queryDto.setBlurry(blurry);
            queryDto.setCurrentPage(currentPage);
            queryDto.setPageSize(pageSize);
            queryDto.setSort(sort);
            queryDto.setOrder(order);
            queryDto.setSubscriber(subscriber);

            IPage<OrderListVO> result = orderService.getOrderPage(queryDto);
            return success(true, result);
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
