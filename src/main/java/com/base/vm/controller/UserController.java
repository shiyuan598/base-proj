package com.base.vm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VUser;
import com.base.vm.entity.dto.AddUserDTO;
import com.base.vm.entity.vo.user.UserPageResponse;
import com.base.vm.entity.vo.user.UserVO;
import com.base.vm.service.UserService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "用户相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends ResultUtil {
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "用户分页列表")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResponse.class)))
    public ResponseEntity<Object> listUsers(@Parameter(description = "模糊搜索关键字") @RequestParam(required = false) String blurry,

                                            @Parameter(description = "当前页码", example = "1") @RequestParam(defaultValue = "1") long currentPage,

                                            @Parameter(description = "每页条数", example = "10") @RequestParam(defaultValue = "10") long pageSize,

                                            @Parameter(description = "排序字段") @RequestParam(required = false) String sort,

                                            @Parameter(description = "排序方向 (asc/desc)") @RequestParam(required = false) String order) {
        try {
            LambdaQueryWrapper<VUser> queryWrapper = new LambdaQueryWrapper<>();
            if (StrUtil.isNotBlank(blurry)) {
                queryWrapper.like(VUser::getName, blurry).or().like(VUser::getUsername, blurry);
            }

            // 处理排序
            if (StringUtils.isNotBlank(sort)) {
                boolean isAsc = StringUtils.isBlank(order) || "asc".equalsIgnoreCase(order);
                // 根据不同字段排序
                switch (sort.toLowerCase()) {
                    case "username":
                        queryWrapper.orderBy(true, isAsc, VUser::getUsername);
                        break;
                    case "role":
                        queryWrapper.orderBy(true, isAsc, VUser::getRole);
                        break;
                    case "name":
                        queryWrapper.orderBy(true, isAsc, VUser::getName);
                        break;
                    default:
                        // 默认按id降序
                        queryWrapper.orderByDesc(VUser::getId);
                }
            } else {
                // 默认按id降序
                queryWrapper.orderByDesc(VUser::getId);
            }
            IPage<VUser> userPage = userService.page(new Page<>(currentPage, pageSize), queryWrapper);
            List<VUser> userList = userPage.getRecords();
            List<UserVO> userVOList = new ArrayList<>();
            for (VUser user : userList) {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                userVOList.add(userVO);
            }
            Page<UserVO> userVOPage = new Page<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
            userVOPage.setRecords(userVOList);
            return success(true, userVOPage);
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "查询司机")
    @GetMapping("/driver")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserVO.class)))
    public ResponseEntity<Object> getDriver(@Parameter(description = "姓名") @RequestParam(required = false) String name) {
        try {
            LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
            if (StrUtil.isNotBlank(name)) {
                wrapper.like(VUser::getName, name);
            }
            wrapper.eq(VUser::getRole, 2);
            List<VUser> userList = userService.list(wrapper);
            List<UserVO> userVOList = new ArrayList<>();
            for (VUser user : userList) {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                userVOList.add(userVO);
            }
            return success(true, userVOList);
        } catch (RuntimeException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "添加用户")
    @PostMapping
    public ResponseEntity<Object> addUser(@Parameter(description = "用户") @RequestBody AddUserDTO userDto) {
        try {
            VUser user = new VUser();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setName(userDto.getName());
            user.setTelephone(userDto.getTelephone());
            user.setRole(userDto.getRole());

            LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(VUser::getUsername, user.getUsername());
            long count = userService.count(wrapper);
            if (count > 0) {
                throw new RuntimeException("用户名已存在");
            }
            userService.save(user);
            return success(true, "成功");
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "更新用户")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@Parameter(description = "用户Id") @PathVariable Integer id, @Parameter(description = "用户信息") @RequestBody VUser user) {
        try {
            VUser newUser = userService.getById(id);
            Optional.ofNullable(user.getName()).ifPresent(newUser::setName);
            Optional.ofNullable(user.getTelephone()).ifPresent(newUser::setTelephone);
            userService.updateById(newUser);
            return success(true, "成功");
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<Object> deleteUser(@Parameter(description = "用户Id") @PathVariable Integer id) {
        try {
            userService.removeById(id);
            return success(true, "成功");
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }
}