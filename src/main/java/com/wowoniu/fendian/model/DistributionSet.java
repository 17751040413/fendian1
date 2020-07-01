package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuany
 * @date 2020-07-01
 */
@ApiModel("分销活动设置")
public class DistributionSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("状态（N:禁用；Y:启用）")
    private String state;

    @ApiModelProperty("分销佣金比例（整数+%）")
    private Integer commissionRatio;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("分销优惠券集合")
    private List<DistributionCoupon> distributionCouponList;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCommissionRatio() {
        return commissionRatio;
    }

    public void setCommissionRatio(Integer commissionRatio) {
        this.commissionRatio = commissionRatio;
    }

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }

    public List<DistributionCoupon> getDistributionCouponList() {
        return distributionCouponList;
    }

    public void setDistributionCouponList(List<DistributionCoupon> distributionCouponList) {
        this.distributionCouponList = distributionCouponList;
    }
}
