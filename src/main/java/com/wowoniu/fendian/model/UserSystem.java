package com.wowoniu.fendian.model;


public class UserSystem {

  private String id;
  private String userId;
  private String systemId;
  private java.util.Date expireTime;


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


  public java.util.Date getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(java.util.Date expireTime) {
    this.expireTime = expireTime;
  }

}
