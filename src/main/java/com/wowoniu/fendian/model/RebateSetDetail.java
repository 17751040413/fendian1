package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-01
 */
@ApiModel("返利活动设置详情")
public class RebateSetDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("返利活动设置ID")
    private String rebateId;

    @ApiModelProperty("等级序列")
    private Integer level;

    @ApiModelProperty("等级名称")
    private String levelName;

    @ApiModelProperty("获取条件（累计邀请人数）")
    private Integer gainFactor;

    @ApiModelProperty("买单返现比例（整数+%）")
    private Integer rebateRatio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRebateId() {
        return rebateId;
    }

    public void setRebateId(String rebateId) {
        this.rebateId = rebateId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getGainFactor() {
        return gainFactor;
    }

    public void setGainFactor(Integer gainFactor) {
        this.gainFactor = gainFactor;
    }

    public Integer getRebateRatio() {
        return rebateRatio;
    }

    public void setRebateRatio(Integer rebateRatio) {
        this.rebateRatio = rebateRatio;
    }
}
