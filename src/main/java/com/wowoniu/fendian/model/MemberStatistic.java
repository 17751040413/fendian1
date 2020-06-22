package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yuany
 * @date 2020-06-22
 */
@ApiModel("会员统计")
public class MemberStatistic {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("总计会员数")
    private Integer totalMember;

    @ApiModelProperty("总计发放券数")
    private Integer totalCoupon;

    @ApiModelProperty("总计使用券数")
    private Integer totalUseCoupon;

    @ApiModelProperty("今日新增会员数")
    private Integer todayMember;

    @ApiModelProperty("今日发放券数")
    private Integer totayCoupon;

    @ApiModelProperty("今日使用券数")
    private Integer totayUseCoupon;

    @ApiModelProperty("最后更新时间(用于归零今日数据)")
    private java.util.Date lastUpdateTime;


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


    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }


    public Integer getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(Integer totalCoupon) {
        this.totalCoupon = totalCoupon;
    }


    public Integer getTotalUseCoupon() {
        return totalUseCoupon;
    }

    public void setTotalUseCoupon(Integer totalUseCoupon) {
        this.totalUseCoupon = totalUseCoupon;
    }


    public Integer getTodayMember() {
        return todayMember;
    }

    public void setTodayMember(Integer todayMember) {
        this.todayMember = todayMember;
    }


    public Integer getTotayCoupon() {
        return totayCoupon;
    }

    public void setTotayCoupon(Integer totayCoupon) {
        this.totayCoupon = totayCoupon;
    }


    public Integer getTotayUseCoupon() {
        return totayUseCoupon;
    }

    public void setTotayUseCoupon(Integer totayUseCoupon) {
        this.totayUseCoupon = totayUseCoupon;
    }


    public java.util.Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(java.util.Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
