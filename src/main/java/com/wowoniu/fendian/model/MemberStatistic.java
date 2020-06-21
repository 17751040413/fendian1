package com.wowoniu.fendian.model;


public class MemberStatistic {

  private String id;
  private String userId;
  private Integer totalMember;
  private Integer totalCoupon;
  private Integer totalUseCoupon;
  private Integer todayMember;
  private Integer totayCoupon;
  private Integer totayUseCoupon;
  private java.util.Date lastUpdateTime;


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


  public Integer getTotalMember() {
    return totalMember;
  }

  public void setTotalMember(Integer totalMember) {
    this.totalMember = totalMember;
  }


  public Integer getTotalCoupon() {
    return totalCoupon;
  }

  public void setTotalCoupon(Integer totalCoupon) {
    this.totalCoupon = totalCoupon;
  }


  public Integer getTotalUseCoupon() {
    return totalUseCoupon;
  }

  public void setTotalUseCoupon(Integer totalUseCoupon) {
    this.totalUseCoupon = totalUseCoupon;
  }


  public Integer getTodayMember() {
    return todayMember;
  }

  public void setTodayMember(Integer todayMember) {
    this.todayMember = todayMember;
  }


  public Integer getTotayCoupon() {
    return totayCoupon;
  }

  public void setTotayCoupon(Integer totayCoupon) {
    this.totayCoupon = totayCoupon;
  }


  public Integer getTotayUseCoupon() {
    return totayUseCoupon;
  }

  public void setTotayUseCoupon(Integer totayUseCoupon) {
    this.totayUseCoupon = totayUseCoupon;
  }


  public java.util.Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(java.util.Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

}
