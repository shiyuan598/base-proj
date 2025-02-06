package com.base.vm.entity.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "订单列表")
public class OrderListVO {
    @Schema(description = "订单ID")
    private Integer id;
    @Schema(description = "预订人ID")
    private String subscriber;
    @Schema(description = "预订人姓名")
    private String subscriberName;
    @Schema(description = "预订人电话")
    private String subscriberPhone;
    @Schema(description = "所属模块")
    private String module;
    @Schema(description = "所属模块名称")
    private String moduleName;

    @Schema(description = "所属项目")
    private String project;
    @Schema(description = "所属项目名称")
    private String projectName;
    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Schema(description = "使用时长")
    private String duration;
    @Schema(description = "车辆Id")
    private String vehicleId;
    @Schema(description = "车辆编号")
    private String vehicleNo;
    @Schema(description = "用车地点")
    private String address;
    @Schema(description = "用车目的")
    private String purpose;
    @Schema(description = "用车路线")
    private String route;
    @Schema(description = "备注")
    private String subscribeNote;
    @Schema(description = "意向司机")
    private Integer inclinationDriverId;
    @Schema(description = "意向司机姓名")
    private Integer inclinationDriverName;

    @Schema(description = "审批人")
    private String approver;
    @Schema(description = "审批人姓名")
    private String approverName;
    @Schema(description = "审批意见")
    private String comment;
    @Schema(description = "司机")
    private String driver;
    @Schema(description = "司机姓名")
    private String driverName;
    @Schema(description = "司机电话")
    private String driverPhone;

    @Schema(description = "订单状态")
    private Integer state;
    @Schema(description = "订单状态名称")
    private Integer stateName;
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}

