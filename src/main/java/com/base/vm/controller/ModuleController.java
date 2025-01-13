package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VModule;
import com.base.vm.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleController extends ResultUtil {
    @Autowired
    private ModuleService moduleService;
    @GetMapping
    public ResponseEntity<Object> index() {
        try {
            List<VModule> data = moduleService.findAll();
            return success(true, data);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
