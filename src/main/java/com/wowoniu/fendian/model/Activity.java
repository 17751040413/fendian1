package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * activity
 * @author 
 */

public class Activity implements Serializable {
    private Integer id;

    private String name;

    /**
     * 目的
     */
    private String objective;

    /**
     * 图片地址
     */
    private String img;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}