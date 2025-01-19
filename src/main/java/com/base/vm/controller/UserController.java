package com.base.vm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends ResultUtil {
    private final UserService userService;

    @Operation(summary = "用户分页列表")
    @GetMapping
    public ResponseEntity<Object> listUsers(@Parameter(description = "模糊搜索关键字")
                                            @RequestParam(required = false) String blurry,

                                            @Parameter(description = "当前页码", example = "1")
                                            @RequestParam(defaultValue = "1") long currentPage,

                                            @Parameter(description = "每页条数", example = "10")
                                            @RequestParam(defaultValue = "10") long pageSize,

                                            @Parameter(description = "排序字段")
                                            @RequestParam(required = false) String sort,

                                            @Parameter(description = "排序方向 (asc/desc)")
                                            @RequestParam(required = false) String order
    ) {
        try {
            QueryDTO queryDto = new QueryDTO();
            queryDto.setBlurry(blurry);
            queryDto.setCurrentPage(currentPage);
            queryDto.setPageSize(pageSize);
            queryDto.setSort(sort);
            queryDto.setOrder(order);
            return success(true, userService.page(new Page<>(queryDto.getCurrentPage(), queryDto.getPageSize())));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}