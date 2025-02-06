package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName v_user
 */
@TableName(value ="v_user")
@Data
public class VUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    private String telephone;

    private Integer role;

    @JsonIgnore
    private String token;

    private static final long serialVersionUID = 1L;
}