package com.wowoniu.fendian.model;


public class MemberSet {

  private String id;
  private String userId;
  private Integer couponReachDay;
  private Integer effectiveTime;
  private String phoneNo;
  private Integer state;


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


  public Integer getCouponReachDay() {
    return couponReachDay;
  }

  public void setCouponReachDay(Integer couponReachDay) {
    this.couponReachDay = couponReachDay;
  }


  public Integer getEffectiveTime() {
    return effectiveTime;
  }

  public void setEffectiveTime(Integer effectiveTime) {
    this.effectiveTime = effectiveTime;
  }


  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

}
