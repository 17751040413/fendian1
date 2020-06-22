package com.wowoniu.fendian.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("补贴分类表")
public class UseUserSubsidy {

  private String id;
  @ApiModelProperty("用户id")
  private String userId;
  @ApiModelProperty("补贴描述")
  private String subsidyName;
  @ApiModelProperty("领取条件")
  private Integer conditions;
  @ApiModelProperty("补贴金额")
  private Integer money;
  @ApiModelProperty("是否已领取")
  private Integer receiveFlg;
  @ApiModelProperty("创建时间")
  private java.util.Date createTime;


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


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
