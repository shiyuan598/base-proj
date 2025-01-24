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
    @Schema(description = "状态，0:不可用，1:可用")
    private Integer role;
    @NotBlank
    @Schema(description = "手机号")
    private String telephone;
}
