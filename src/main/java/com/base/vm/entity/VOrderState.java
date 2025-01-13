package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName v_order_state
 */
@TableName(value ="v_order_state")
@Data
public class VOrderState implements Serializable {
    private Integer id;

    private Integer state;

    private String name;

    private static final long serialVersionUID = 1L;
}
