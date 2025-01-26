package com.base.vm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VVehicle;
import com.base.vm.entity.dto.AddVehicleDTO;
import com.base.vm.entity.dto.QueryDTO;
import com.base.vm.entity.dto.UpdateVehicleDTO;
import com.base.vm.entity.vo.vehicle.VehicleDictListResponse;
import com.base.vm.entity.vo.vehicle.VehicleListVO;
import com.base.vm.entity.vo.vehicle.VehiclePageResponse;
import com.base.vm.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiclePageResponse.class)))
    })
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
            IPage<VehicleListVO> result = vehicleService.getVehicleVOPage(queryDto);
            return success(true, result);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "车辆总数")
    @GetMapping("/count")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class)))
    })
    public ResponseEntity<Object> countVehicle() {
        try {
            return success(true, vehicleService.count());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "可用车辆列表(字典)")
    @GetMapping("/list/available")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehicleDictListResponse.class)))
    })
    public ResponseEntity<Object> allVehicles() {
        try {
            return success(true, vehicleService.getAvailableVehicles());
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "添加车辆")
    @PostMapping()
    public ResponseEntity<Object> addVehicle(@Parameter(description = "车辆信息") @RequestBody AddVehicleDTO vehicleDTO) {
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

    @Operation(summary = "检查车牌号是否存在")
    @GetMapping("/check/exist")
    @ApiResponse(responseCode = "200", description = "查询成功",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = "object",
                            properties = {
                                    @StringToClassMapItem(key = "success", value = Boolean.class),
                                    @StringToClassMapItem(key = "data", value = Boolean.class)
                            })))
    public ResponseEntity<Object> checkExist(@Parameter(description = "车辆编号") @RequestParam String vehicleNo) {
        try {
            LambdaQueryWrapper<VVehicle> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(VVehicle::getVehicleNo, vehicleNo);
            return success(true, vehicleService.exists(wrapper));
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "更新车辆")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@Parameter(description = "车辆ID") @PathVariable(value = "id") Integer id,  @Parameter(description = "车辆信息") @RequestBody UpdateVehicleDTO vehicleDTO) {
        try {
            VVehicle vehicle = vehicleService.getById(id);
            Optional.ofNullable(vehicleDTO.getVehicleNo()).ifPresent(vehicle::setVehicleNo);
            Optional.ofNullable(vehicleDTO.getProject()).ifPresent(vehicle::setProject);
            Optional.ofNullable(vehicleDTO.getState()).ifPresent(vehicle::setState);
            Optional.ofNullable(vehicleDTO.getPlace()).ifPresent(vehicle::setPlace);
            Optional.ofNullable(vehicleDTO.getReason()).ifPresent(vehicle::setReason);
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
