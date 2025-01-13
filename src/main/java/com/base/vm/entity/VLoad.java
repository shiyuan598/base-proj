package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value ="v_load")
@Data
public class VLoad implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}
