package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.service.LoadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "车辆挂载")
@RestController
@RequiredArgsConstructor
@RequestMapping("/load")
public class LoadController extends ResultUtil {
    @Autowired
    private LoadService loadService;

    @Operation(summary = "车辆挂载列表(字典)")
    @GetMapping("/dict")
    public ResponseEntity<Object> listLoadDict() {
        try {
            return success(true, loadService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
