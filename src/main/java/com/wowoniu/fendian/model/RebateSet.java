package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author
 * @date 2020-07-01
 */
@ApiModel("返利活动设置")
public class RebateSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("状态（N：关；Y：开）")
    private String state;

    @ApiModelProperty("领取时是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("启用新客户专享（N：禁用；Y：启用）")
    private String newUserEnable;

    @ApiModelProperty("返利设置（N：禁用；Y：启用）")
    private String rebateEnable;

    @ApiModelProperty("允许老用户参与助力（N：禁用；Y：启用）")
    private String oldUserEnable;

    @ApiModelProperty("充值设置（N：禁用；Y：启用）")
    private String recharge;

    @ApiModelProperty("充值返现设置（N:禁用；Y:启用）")
    private String rechargeRebateEnable;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("会员有效期(1-36)")
    private Integer effectiveTime;

    @ApiModelProperty("返现比列（整数+%）")
    private Integer proportion;

    @ApiModelProperty("新客户比例（整数+%）")
    private Integer newUserProportion;

    @ApiModelProperty("裂变助力人数（个）")
    private Integer fissionHelpNumber;

    @ApiModelProperty("奖励助力的新用户金额（分）")
    private Integer firstHelpMoney;

    @ApiModelProperty("奖励助力的老用户金额（分）")
    private Integer oldHelpMoney;

    @ApiModelProperty("返利规则")
    private String rule;

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

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public String getNewUserEnable() {
        return newUserEnable;
    }

    public void setNewUserEnable(String newUserEnable) {
        this.newUserEnable = newUserEnable;
    }

    public String getRebateEnable() {
        return rebateEnable;
    }

    public void setRebateEnable(String rebateEnable) {
        this.rebateEnable = rebateEnable;
    }

    public String getOldUserEnable() {
        return oldUserEnable;
    }

    public void setOldUserEnable(String oldUserEnable) {
        this.oldUserEnable = oldUserEnable;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public String getRechargeRebateEnable() {
        return rechargeRebateEnable;
    }

    public void setRechargeRebateEnable(String rechargeRebateEnable) {
        this.rechargeRebateEnable = rechargeRebateEnable;
    }

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }

    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getNewUserProportion() {
        return newUserProportion;
    }

    public void setNewUserProportion(Integer newUserProportion) {
        this.newUserProportion = newUserProportion;
    }

    public Integer getFissionHelpNumber() {
        return fissionHelpNumber;
    }

    public void setFissionHelpNumber(Integer fissionHelpNumber) {
        this.fissionHelpNumber = fissionHelpNumber;
    }

    public Integer getFirstHelpMoney() {
        return firstHelpMoney;
    }

    public void setFirstHelpMoney(Integer firstHelpMoney) {
        this.firstHelpMoney = firstHelpMoney;
    }

    public Integer getOldHelpMoney() {
        return oldHelpMoney;
    }

    public void setOldHelpMoney(Integer oldHelpMoney) {
        this.oldHelpMoney = oldHelpMoney;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
