package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;

@ApiModel("系统购买表")
public class UserPaySystem {

  private String id;
  private String userId;
  private String systemId;
  private Integer count;
  private Integer totayMoney;
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


  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }


  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }


  public Integer getTotayMoney() {
    return totayMoney;
  }

  public void setTotayMoney(Integer totayMoney) {
    this.totayMoney = totayMoney;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
