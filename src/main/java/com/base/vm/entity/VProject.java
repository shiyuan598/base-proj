package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName v_project
 */
@TableName(value ="v_project")
@Data
public class VProject implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}