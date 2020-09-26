package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author
 * @date 2020-07-04
 */
@ApiModel("商品分类详情")
public class WaresSortDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("分类ID")
    private String sortId;

    @ApiModelProperty("状态（N：禁用；Y：启用）")
    private String state;

    @ApiModelProperty("排序")
    private Integer row;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("置顶排序")
    private Integer topRow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopRow() {
        return topRow;
    }

    public void setTopRow(Integer topRow) {
        this.topRow = topRow;
    }
}
