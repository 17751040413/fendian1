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
    @ApiModelProperty("是否删除")
    private int state;
    @ApiModelProperty("店铺logo")
    private String shopLogo;
    @ApiModelProperty("店铺地址")
    private String shopAddress;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("纬度")
    private Double lat;
    @ApiModelProperty("经度")
    private Double lng;
    @ApiModelProperty("店铺类别（0：服饰；1：零食；2：餐饮；3：水果；4：生鲜）")
    private String type;
    @ApiModelProperty("提货金")
    private Double takeMoney;
    @ApiModelProperty("粉丝")
    private Integer fans;

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Double getTakeMoney() {
        return takeMoney;
    }

    public void setTakeMoney(Double takeMoney) {
        this.takeMoney = takeMoney;
    }

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

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
