package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("会员消费记录")
public class MemberConsume implements Serializable {

    private int consume;

    private String memberId;

    private Timestamp time;

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
}
