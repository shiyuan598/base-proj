package com.base.vm.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    @Schema(description = "用户角色 1: 管理员, 2:司机,3:普通用户")
    private Integer role = 3;
    @NotBlank
    @Schema(description = "手机号")
    private String telephone;
}
