package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/load")
public class LoadController extends ResultUtil {
    @Autowired
    private LoadService loadService;

    @GetMapping
    public ResponseEntity<Object> index() {
        try {
            return success(true, loadService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
