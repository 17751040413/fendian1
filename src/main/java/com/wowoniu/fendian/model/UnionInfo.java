package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * union_info
 * @author 
 */

public class UnionInfo implements Serializable {
    /**
     * 联盟信息
     */
    private String id;

    /**
     * 联盟名
     */
    private String unionName;

    /**
     * 盟主id
     */
    private String unionLeaderId;

    /**
     * 盟主名
     */
    private String unionLeaderName;

    /**
     * 盟主手机号
     */
    private String unionLeaderPhone;

    /**
     * 联盟logo
     */
    private String unionLogo;

    /**
     * 简介
     */
    private String brief;

    /**
     * 规则
     */
    private String rule;

    /**
     * 领取规则 0--无条件 1--加微信
     */
    private Integer receiveType;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    private String unionLeaderWechatImg;

    private int isOpen;

    /**
     * 创建时间
     */
    private Date createTime;


    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public String getUnionLeaderWechatImg() {
        return unionLeaderWechatImg;
    }

    public void setUnionLeaderWechatImg(String unionLeaderWechatImg) {
        this.unionLeaderWechatImg = unionLeaderWechatImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionLeaderId() {
        return unionLeaderId;
    }

    public void setUnionLeaderId(String unionLeaderId) {
        this.unionLeaderId = unionLeaderId;
    }

    public String getUnionLeaderName() {
        return unionLeaderName;
    }

    public void setUnionLeaderName(String unionLeaderName) {
        this.unionLeaderName = unionLeaderName;
    }

    public String getUnionLeaderPhone() {
        return unionLeaderPhone;
    }

    public void setUnionLeaderPhone(String unionLeaderPhone) {
        this.unionLeaderPhone = unionLeaderPhone;
    }

    public String getUnionLogo() {
        return unionLogo;
    }

    public void setUnionLogo(String unionLogo) {
        this.unionLogo = unionLogo;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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

    private static final long serialVersionUID = 1L;
}