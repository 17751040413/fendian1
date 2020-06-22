package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("系统购买表")
public class UserPaySystem {

  private String id;
  @ApiModelProperty("用户id")
  private String userId;
  @ApiModelProperty("系统id")
  private String systemId;
  @ApiModelProperty("购买数量")
  private Integer count;
  @ApiModelProperty("总金额")
  private Integer totalMoney;
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


  public Integer getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(Integer totalMoney) {
    this.totalMoney = totalMoney;
  }

  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
