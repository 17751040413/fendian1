package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("分销商联盟总计")
public class DistributionAlliance implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("总发券数")
    private int number;
    @ApiModelProperty("佣金收益（分）")
    private int money;
    @ApiModelProperty("总用券数")
    private int use;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
