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

    private String subscribenote;

    private String module;

    private Integer vehicleid;

    private String vehicleno;

    private String project;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    private String address;

    private String purpose;

    private String route;

    @TableField("`load`")
    private String load;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date updatetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date createtime;

    private String approver;

    private String driver;

    private Integer state;

    private String comment;

    private Integer inclinationdriver;

    private static final long serialVersionUID = 1L;
}
