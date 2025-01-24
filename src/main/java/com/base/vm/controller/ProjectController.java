package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VProject;
import com.base.vm.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "所属项目")
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@ApiResponse(responseCode = "200", description = "查询成功",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = VProject.class)))
public class ProjectController extends ResultUtil {
    private final ProjectService projectService;

    @Operation(summary = "所属项目字典列表")
    @GetMapping
    public ResponseEntity<Object> index() {
        try {
            return success(true, projectService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
