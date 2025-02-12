package com.base.vm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.base.common.exception.BadRequestException;
import com.base.common.utils.JwtUtils;
import com.base.common.utils.ResultUtil;
import com.base.vm.entity.VUser;
import com.base.vm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "认证相关")
@RestController
@RequestMapping("/auth")
public class AuthController extends ResultUtil {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Parameter(description = "用户名") @RequestParam String username, @Parameter(description = "密码") @RequestParam String password) {
        try {
            // 查询用户信息
            LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(VUser::getUsername, username);
            VUser user =  userService.getOne(wrapper);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                // 密码匹配，生成 JWT Token
                String token = jwtUtils.generateToken(username);
                user.setPassword(""); // 抹掉密码
                user.setToken(token);
                return success(true, user);
            } else {
                // 用户名或密码错误
                return success(true, "Invalid username or password");
            }
        } catch (BadRequestException e) {
            return fail(false, "失败");
        }
    }

    @Operation(summary = "注册用户")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Parameter(description = "用户信息") @RequestBody VUser user) {
        try {
            // 检查用户名是否已存在
            LambdaQueryWrapper<VUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(VUser::getUsername, user.getUsername());
            long count = userService.count(wrapper);
            if (count > 0) {
                throw new RuntimeException("用户名已存在");
            }

            // 注册用户
            user.setPassword(passwordEncoder.encode(user.getPassword())); // 加密密码
            userService.save(user);
            // 生成 JWT Token
            String token = jwtUtils.generateToken(user.getUsername());
            user.setPassword(""); // 抹掉密码
            user.setToken(token);
            return success(true, user);
        } catch (Exception e) {
            return fail(false, "Registration failed");
        }
    }

    @Operation(summary = "忘记密码")
    @PostMapping("/forgetPassword")
    public ResponseEntity<Object> forgetPassword(
            @Parameter(description = "用户名") @RequestParam String username,
            @Parameter(description = "手机号") @RequestParam String telephone,
            @Parameter(description = "新密码") @RequestParam String newPassword) {
        try {
            boolean result = userService.forgetPassword(username, telephone, newPassword);
            if (result) {
                return success(true, "密码修改成功");
            } else {
                return success(true, "用户名或手机号不正确，密码修改失败");
            }
        } catch (Exception e) {
            return fail(false, "密码修改过程中出现错误");
        }
    }
}
