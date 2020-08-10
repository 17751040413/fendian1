package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * activity
 * @author 
 */
@Data
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

    private static final long serialVersionUID = 1L;
}