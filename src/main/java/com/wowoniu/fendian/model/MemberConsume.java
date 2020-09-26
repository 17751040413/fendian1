package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("会员消费记录")
public class MemberConsume implements Serializable {
    @ApiModelProperty("金额")
    private int consume;
    @ApiModelProperty("会员表ID")
    private String memberId;
    @ApiModelProperty("记录时间")
    private Timestamp time;
    @ApiModelProperty("类型（0：返现到账，1：到店消费，2：充值）")
    private String type;

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
