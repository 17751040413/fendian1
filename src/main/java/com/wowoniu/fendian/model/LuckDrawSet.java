package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-05
 */
@ApiModel("抽奖活动设置")
public class LuckDrawSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("开始时间")
    private DateTime startTime;

    @ApiModelProperty("结束时间")
    private DateTime endTime;

    @ApiModelProperty("参加活动是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("单人每天最多抽奖次数")
    private Integer personDay;

    @ApiModelProperty("单人最多可中奖次数")
    private Integer personFrequency;

    @ApiModelProperty("每次抽奖中奖概率（整数+%）")
    private Integer probability;

    @ApiModelProperty("增加次数")
    private Integer increaseTimes;

    @ApiModelProperty("活动描述")
    private String describle;

    @ApiModelProperty("活动图片地址（最多9张‘，’分隔）")
    private String pictureUrl;

    @ApiModelProperty("活动规则")
    private String rule;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("背景音乐ID")
    private String musicId;

    @ApiModelProperty("类型（0：转盘；1：砸金蛋）")
    private String type;

    @ApiModelProperty("今日浏览次数")
    private Integer todayBrowse;

    @ApiModelProperty("总浏览次数")
    private Integer browse;

    @ApiModelProperty("今日领券数")
    private Integer todayReceive;

    @ApiModelProperty("总领券数")
    private Integer receive;

    @ApiModelProperty("今日用券数")
    private Integer todayUse;

    @ApiModelProperty("总用券数")
    private Integer use;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public Integer getPersonDay() {
        return personDay;
    }

    public void setPersonDay(Integer personDay) {
        this.personDay = personDay;
    }

    public Integer getPersonFrequency() {
        return personFrequency;
    }

    public void setPersonFrequency(Integer personFrequency) {
        this.personFrequency = personFrequency;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Integer getIncreaseTimes() {
        return increaseTimes;
    }

    public void setIncreaseTimes(Integer increaseTimes) {
        this.increaseTimes = increaseTimes;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTodayBrowse() {
        return todayBrowse;
    }

    public void setTodayBrowse(Integer todayBrowse) {
        this.todayBrowse = todayBrowse;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getTodayReceive() {
        return todayReceive;
    }

    public void setTodayReceive(Integer todayReceive) {
        this.todayReceive = todayReceive;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Integer getTodayUse() {
        return todayUse;
    }

    public void setTodayUse(Integer todayUse) {
        this.todayUse = todayUse;
    }

    public Integer getUse() {
        return use;
    }

    public void setUse(Integer use) {
        this.use = use;
    }
}
