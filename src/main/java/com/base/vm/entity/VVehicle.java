package com.base.vm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName v_vehicle
 */
@TableName(value ="v_vehicle")
@Data
public class VVehicle implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("`vehicleno`")
    private String vehicleNo;

    private String project;

    // 对应proejct表的name字段
    @TableField(exist = false)
    private String projectName;

    private String place;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private Double lon;

    private Double lat;

    private String reason;

    private static final long serialVersionUID = 1L;
}