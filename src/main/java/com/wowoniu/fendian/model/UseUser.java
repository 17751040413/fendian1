package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户")
public class UseUser {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("登录手机号")
    private String loginName;
    @ApiModelProperty("微信openid")
    private String wechatId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String headImg;
    @ApiModelProperty("父级id")
    private String parentId;
    @ApiModelProperty("父级昵称")
    private String parentName;
    @ApiModelProperty("层级")
    private Integer hierarchy;
    @ApiModelProperty("角色1")
    private String roleId1;
    @ApiModelProperty("角色2")
    private String roleId2;
    @ApiModelProperty("我的团队人数")
    private Integer teamNumber;
    @ApiModelProperty("团队购买系统数")
    private Integer systemNumber;
    @ApiModelProperty("团队今天新增人数")
    private Integer todayTermNumber;
    @ApiModelProperty("是否开通系统")
    private Integer openSystemFlg;
    @ApiModelProperty("推广收益")
    private Integer promotionIncome;
    @ApiModelProperty("余额")
    private Integer balance;
    @ApiModelProperty("推广码")
    private String promotionCode;
    @ApiModelProperty("设备码")
    private String identification;
    @ApiModelProperty("创建时间")
    private java.util.Date createTime;
    private int state;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }


    public String getRoleId1() {
        return roleId1;
    }

    public void setRoleId1(String roleId1) {
        this.roleId1 = roleId1;
    }


    public String getRoleId2() {
        return roleId2;
    }

    public void setRoleId2(String roleId2) {
        this.roleId2 = roleId2;
    }


    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }


    public Integer getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(Integer systemNumber) {
        this.systemNumber = systemNumber;
    }


    public Integer getTodayTermNumber() {
        return todayTermNumber;
    }

    public void setTodayTermNumber(Integer todayTermNumber) {
        this.todayTermNumber = todayTermNumber;
    }


    public Integer getOpenSystemFlg() {
        return openSystemFlg;
    }

    public void setOpenSystemFlg(Integer openSystemFlg) {
        this.openSystemFlg = openSystemFlg;
    }


    public Integer getPromotionIncome() {
        return promotionIncome;
    }

    public void setPromotionIncome(Integer promotionIncome) {
        this.promotionIncome = promotionIncome;
    }


    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }


    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}
