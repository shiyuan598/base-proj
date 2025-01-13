package com.base.vm.controller;

import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController extends ResultUtil {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Object> index() {
        try {
            return success(true, projectService.findAll());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
