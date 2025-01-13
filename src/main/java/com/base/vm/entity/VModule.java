package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName v_module
 */
@TableName(value ="v_module")
@Data
public class VModule implements Serializable {
    private Integer id;

    private String name;

    private Integer pid;

    private static final long serialVersionUID = 1L;

}