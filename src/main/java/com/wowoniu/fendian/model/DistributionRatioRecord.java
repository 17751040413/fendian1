package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("分销比例记录")
public class DistributionRatioRecord implements Serializable {
    @ApiModelProperty("比例(整数+%)")
    private int ratio;
    @ApiModelProperty("记录时间")
    private Timestamp time;
    @ApiModelProperty("分销ID")
    private String distributionId;

    @ApiModelProperty("是否生效（0:生效 1失效）")
    private String use;

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }
}
