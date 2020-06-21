package com.wowoniu.fendian.model;


public class UseUserSubsidy {

  private String id;
  private String userId;
  private String subsidyName;
  private Integer conditions;
  private Integer money;
  private Integer receiveFlg;
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
