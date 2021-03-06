package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.UnionInfo;
import com.wowoniu.fendian.utils.Result;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UnionService {
    /**
     * 根据盟主id获取联盟信息
     * @param leaderId
     * @return
     */
    Result getUnionInfo( String leaderId);

    /**
     * 商圈3-1
     * @param id
     * @return
     */
    Result getUnionLeaderMan(String id);

    /**
     * 商圈订单 3-2-1
     * @return
     */
    Result unionOrder(String userid,String unionId);

    /**
     * 所有联盟券 3-3-1
     * @param couponType
     * @return
     */
    Result unionCoupon(int couponType,String shopName,String unionId);

    /**
     * 结束派发 3-3-1
     * @param unionCouponId
     * @return
     */
    Result endDisCoupon(String id);

    /**
     * 优惠券详情
     * @param id
     * @return
     */
    Result unionCouponInfo(String id);

    /**
     * 优惠券审核
     * @param id
     * @param exmType
     * @return
     */
    Result unionCouponExamine(String id,int exmType);

    /**
     * 联盟设置
     * @param id
     * @param unionName
     * @param leaderPhone
     * @return
     */
    Result unionSet(String id,String unionName,String leaderPhone);

    /**
     * 规则修改
     * @param brief
     * @param rule
     * @return
     */
    Result unionRule(String brief,String rule,String id);

    /**
     * 联盟位置设置
     * @param lng
     * @param lat
     * @param id
     * @return
     */
    Result unionPositionSet(double lng,double lat,String id);

    /**
     * 设置领取条件
     * @return
     */
    Result unionMemberReceive(int type,String id);

    /**
     * 联盟店铺
     * @param id
     * @param shopName
     * @return
     */
    Result unionShops(String id,String shopName);

    /**
     * 商圈店铺详情
     * @param unionShopId
     * @param grantType
     * @return
     */
    Result unionShopInfo(String unionShopId,int grantType);

    /**
     * 锁定解锁店铺
     * @param unionShopId
     * @return
     */
    Result lockShop(String unionShopId);

    /**
     *联盟优惠券记录
     * @param unionId
     * @param grantType
     * @return
     */
    Result unionCouponLog(String unionId,int grantType);

    /**
     * 商圈顾客列表
     * @param unionId
     * @param keyWords
     * @return
     */
    Result unionCustomers(String unionId,String keyWords);


    /**
     * 商圈顾客详情
     * @param unionCusId
     * @return
     */
    Result unionCustomerInfo(String unionCusId);

    /**
     * 获取我的提货金
     * @param userid
     * @return
     */
    Result unionCashMoney(String userid);

    /**
     * 为客户开通服务页面
     * @param id
     * @return
     */
    Result goOpenService(String id);

    /**
     * 根据手机号开通服务
     * @param phone
     * @return
     */
    Result phoneOpenService(String phone,String id);

    /**
     * 修改背景图
     * @param img
     * @param unionId
     * @return
     */
    Result updatebeingjing(String img,String unionId);



}
