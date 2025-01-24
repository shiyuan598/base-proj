package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VModule;
import com.base.vm.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "所属模块")
@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
@ApiResponse(responseCode = "200", description = "查询成功",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VModule.class)))
public class ModuleController extends ResultUtil {
    @Autowired
    private ModuleService moduleService;

    @Operation(summary = "所属模块列表(字典)")
    @GetMapping("/dict")
    public ResponseEntity<Object> listModuleDict() {
        try {
            List<VModule> data = moduleService.findAll();
            return success(true, data);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
