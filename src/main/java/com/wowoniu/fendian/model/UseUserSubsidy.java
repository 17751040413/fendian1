package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * use_user_subsidy
 * @author 
 */

public class UseUserSubsidy implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 补贴描述
     */
    private String subsidyName;

    /**
     * 领取条件(团队购买系统数量)
     */
    private Integer conditions;

    /**
     * 领取金额(单位 分)
     */
    private Integer money;

    /**
     * 是否已领取(0--已领取 1--未领取)
     */
    private Integer receiveFlg;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

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

    public String getSubsidyName() {
        return subsidyName;
    }

    public void setSubsidyName(String subsidyName) {
        this.subsidyName = subsidyName;
    }

    public Integer getConditions() {
        return conditions;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getReceiveFlg() {
        return receiveFlg;
    }

    public void setReceiveFlg(Integer receiveFlg) {
        this.receiveFlg = receiveFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}