package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.service.OrderStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderState")
public class OrderStateController extends ResultUtil {
    private final OrderStateService stateService;

    @GetMapping
    public ResponseEntity<Object> index() {
        try {
            return success(true, stateService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
