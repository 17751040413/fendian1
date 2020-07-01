package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-01
 */
@ApiModel("充值详情")
public class RechargeDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("返利活动设置ID")
    private String reableId;

    @ApiModelProperty("充值金额（分）")
    private Integer rechargeMoney;

    @ApiModelProperty("充值赠送金额（分）")
    private Integer giveMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReableId() {
        return reableId;
    }

    public void setReableId(String reableId) {
        this.reableId = reableId;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Integer rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Integer getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(Integer giveMoney) {
        this.giveMoney = giveMoney;
    }
}
