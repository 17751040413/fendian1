package com.wowoniu.fendian.config;

import java.math.BigDecimal;

/**
 * 常量类
 *
 * @author
 * @date 2020-06-30
 */
public class Constants {

    /***************  关闭：NO 开启：YES ***************/
    public static final String NO = "N";

    public static final String YES = "Y";


    /***************
     * 引流类型【
     * 0：会员裂变；1：会员返利；2：店铺分销；3：在线商城；
     * 4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；
     * 9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈 13:联盟 14:排队
     * 】
     ***************/
    public static final String FISSION = "0";
    public static final String REBATE = "1";
    public static final String DISTRIBUTION = "2";
    public static final String SHOPPINGMALL = "3";
    public static final String TURNTABLE = "4";
    public static final String COUPON = "5";
    public static final String RECOMMEND = "6";
    public static final String SECKILL = "7";
    public static final String GROUP = "8";
    public static final String LUCKDRAW = "9";
    public static final String BARGAINING = "10";
    public static final String REDENVELOPES = "11";
    public static final String SHAREFRIENDS = "12";
    public static final String UNION = "13";
    public static final String lineUp = "14";

    /***************
     * 订单状态（0：待付款；1：已付款；2：代发货；3：已发货；4：已完成；5：已关闭）
     * ***************/
    public static final String ORDER_STATE_PENDING_PAYMENT = "0";
    public static final String ORDER_STATE_PAID = "1";
    public static final String ORDER_STATE_NOT_SHIPPED = "2";
    public static final String ORDER_STATE_SHIPPED = "3";
    public static final String ORDER_STATE_COMPLETE = "4";
    public static final String ORDER_STATE_CLOSE = "5";

    /*********************配置**************/


    /********************* 券类型（0：折扣 1：优惠 2：兑换）**************/
    public static final String DISCOUNT = "0";
    public static final String FAVORABLE = "1";
    public static final String EXCHANGE = "2";

    /********************* 状态：0拼团中；1成功；2失败**************/
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";

    /**********图片地址******************/
    /**
     * 策划师微信二维码
     */
    public static final String CEHUAIMG = "http://39.105.40.234:8082/img/cehua.jpg";

    /**
     * 招商经理微信二维码
     */
    public static final String ATTACTIMG = "http://39.105.40.234:8082/img/attract.jpg";

    /**
     * 图片服务器地址
     */
    public static final String IMGPATH = "http://localhost:8082/";

    /**
     * 引流系统价格
     */
    public static final BigDecimal MEMBERPRICE = new BigDecimal(888);



}
