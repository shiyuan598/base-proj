package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends ResultUtil {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAll(QueryDTO queryDto) {
        try {
            return success(true, userService.page(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize())));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}