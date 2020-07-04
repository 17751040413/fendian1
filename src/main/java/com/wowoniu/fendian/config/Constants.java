package com.wowoniu.fendian.config;

/**
 * 常量类
 *
 * @author yuany
 * @date 2020-06-30
 */
public class Constants {

    /***************
     * 引流类型【
     * 0：会员裂变；1：会员返利；2：店铺分销；3：在线商城；
     * 4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；
     * 9：砸金蛋抽奖；10：砍价大战；11：红包裂变券
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
    public static final String SPELL = "8";
    public static final String LUCKDRAW = "9";
    public static final String BARGAINING = "10";
    public static final String REDENVELOPES = "11";

    /***************
     * 订单状态（0：待付款；1：已付款；2：代发货；3：已发货；4：已完成；5：已关闭）
     * ***************/
    public static final String ORDER_STATE_PENDING_PAYMENT = "0";
    public static final String ORDER_STATE_PAID = "1";
    public static final String ORDER_STATE_NOT_SHIPPED = "2";
    public static final String ORDER_STATE_SHIPPED= "3";
    public static final String ORDER_STATE_COMPLETE = "4";
    public static final String ORDER_STATE_CLOSE = "5";


}
