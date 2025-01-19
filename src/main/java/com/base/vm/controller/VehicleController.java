package com.base.vm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.dto.VehicleDTO;
import com.base.vm.entity.vo.VehicleDictVO;
import com.base.vm.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "车辆相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController extends ResultUtil {
    private final VehicleService vehicleService;

    @Operation(summary = "车辆分页列表")
    @GetMapping()
    public ResponseEntity<Object> listVehicle(@Parameter(description = "模糊搜索关键字")
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
            IPage<Map<String, Object>> result = vehicleService.getVehiclePage(queryDto);
            return success(true, result);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "车辆分页列表2")
    @GetMapping("/pageVo")
    public ResponseEntity<Object> listVehicleVo(@Parameter(description = "模糊搜索关键字")
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
            IPage<VehicleDictVO> result = vehicleService.getVehicleVOPage(queryDto);
            return success(true, result);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "车辆列表(字典)")
    @GetMapping("/list/available")
    public ResponseEntity<Object> allVehicles() {
        try {
            return success(true, vehicleService.getAvailableVehicles());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "添加车辆")
    @PostMapping()
    public ResponseEntity<Object> addVehicle(@Parameter(description = "车辆信息") @RequestBody VehicleDTO vehicleDTO) {
        try {
            VVehicle vehicle = new VVehicle();
            vehicle.setVehicleNo(vehicleDTO.getVehicleNo());
            vehicle.setProject(vehicleDTO.getProject());
            vehicle.setState(vehicleDTO.getState());
            vehicle.setPlace(vehicleDTO.getPlace());
            vehicle.setReason(vehicleDTO.getReason());
            vehicleService.save(vehicle);
            return success(true, "成功");
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "更新车辆")
    @PutMapping()
    public ResponseEntity<Object> updateVehicle(@Parameter(description = "车辆信息") @RequestBody VehicleDTO vehicleDTO) {
        try {
            VVehicle vehicle = new VVehicle();
            vehicle.setId(vehicleDTO.getId());
            vehicle.setVehicleNo(vehicleDTO.getVehicleNo());
            vehicle.setProject(vehicleDTO.getProject());
            vehicle.setState(vehicleDTO.getState());
            vehicle.setPlace(vehicleDTO.getPlace());
            vehicle.setReason(vehicleDTO.getReason());
            vehicleService.updateById(vehicle);
            return success(true, "成功");
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "删除车辆")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@Parameter(description = "车辆id") @PathVariable Integer id) {
        try {
            vehicleService.removeById(id);
            return success(true, null);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }


}
