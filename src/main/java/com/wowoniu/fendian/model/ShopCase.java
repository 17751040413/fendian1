package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * shop_case
 * @author 
 */
@ApiModel("案例数据")
public class ShopCase implements Serializable {
    /**
     * 案例id
     */
    @ApiModelProperty("id")
    private String id;

    /**
     * 行业id
     */
    @ApiModelProperty("行业id")
    private String industyrId;

    /**
     * 店铺名
     */
    @ApiModelProperty("案例名")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 店铺名
     */
    private String typeId;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("使用人数")
    private int useCount;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("对应活动")
    private List<Activity> activityList;


    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustyrId() {
        return industyrId;
    }

    public void setIndustyrId(String industyrId) {
        this.industyrId = industyrId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}