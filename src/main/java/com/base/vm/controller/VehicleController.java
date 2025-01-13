package com.base.vm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.vo.VehicleDetailVO;
import com.base.vm.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController extends ResultUtil {
    private final VehicleService vehicleService;

    @GetMapping("/page")
    public ResponseEntity<Object> index(QueryDTO queryDto) {
        try {
            IPage<Map<String, Object>> result = vehicleService.getVehiclePage(queryDto);
            return success(true, result);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @GetMapping("/pageVo")
    public ResponseEntity<Object> indexVo(QueryDTO queryDto) {
        try {
            IPage<VehicleDetailVO> result = vehicleService.getVehicleVOPage(queryDto);
            return success(true, result);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @GetMapping("/list/available")
    public ResponseEntity<Object> allVehicles() {
        try {
            return success(true, vehicleService.getAvailableVehicles());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}
