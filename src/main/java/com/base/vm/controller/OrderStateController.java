package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.service.OrderStateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单状态")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderState")
public class OrderStateController extends ResultUtil {
    private final OrderStateService stateService;

    @Operation(summary = "订单状态列表(字典)")
    @GetMapping("/dict")
    public ResponseEntity<Object> listOrderStateDict() {
        try {
            return success(true, stateService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
