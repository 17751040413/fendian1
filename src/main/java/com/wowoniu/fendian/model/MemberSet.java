package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yuany
 * @date 2020-06-22
 */
@ApiModel("会员活动设置")
public class MemberSet {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("券月到账日期（1~28）")
    private Integer couponReachDay;

    @ApiModelProperty("券有效月数（1-36）")
    private Integer effectiveTime;

    @ApiModelProperty("手机号")
    private String phoneNo;

    @ApiModelProperty("状态（0：关；1：开）")
    private Integer state;


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


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
