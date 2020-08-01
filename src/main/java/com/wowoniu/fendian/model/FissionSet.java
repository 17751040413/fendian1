package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-06-22
 */
@ApiModel("裂变活动设置")
public class FissionSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("券月到账日期（1~28）")
    private Integer couponReachDay;

    @ApiModelProperty("券有效月数（1-36）")
    private Integer effectiveTime;

    @ApiModelProperty("领取时是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("状态（N：关；Y：开）")
    private String state;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getCouponReachDay() {
        return couponReachDay;
    }

    public void setCouponReachDay(Integer couponReachDay) {
        this.couponReachDay = couponReachDay;
    }


    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }
}
