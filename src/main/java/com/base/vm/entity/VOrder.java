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
 * @TableName v_order
 */
@TableName(value ="v_order")
@Data
public class VOrder implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String subscriber;

    @TableField("subscribeNote")
    private String subscribeNote;

    private String module;

    @TableField("vehicleId")
    private Integer vehicleId;

    @TableField("vehicleNo")
    private String vehicleNo;

    private String project;

    @TableField("starttime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @TableField("endtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String address;

    private String purpose;

    private String route;

    @TableField("`load`")
    private String load;

    @TableField("updatetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date updateTime;

    @TableField("createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date createTime;

    private String approver;

    private String driver;

    private Integer state;

    private String comment;

    @TableField("inclinationDriver")
    private Integer inclinationDriver;

    private static final long serialVersionUID = 1L;
}
