package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName v_order_state
 */
@TableName(value ="v_order_state")
@Data
public class VOrderState implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer state;

    private String name;

    private static final long serialVersionUID = 1L;
}
