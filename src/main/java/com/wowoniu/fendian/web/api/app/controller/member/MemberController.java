package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.service.MemberStatisticService;
import com.wowoniu.fendian.utils.Result;
import com.wowoniu.fendian.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理控制层
 *
 * @author yuany
 * @date 2020-06-22
 */
@Api(value = "会员管理控制层", tags = "会员统计管理统计接口")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberStatisticService memberStatisticService;

    /**
     * 根据用户ID获取其会员及当日数据统计，及活动列表
     *
     * @return
     */
    @ApiOperation(value = "会员管理--会员统计数据,及活动列表", tags = "根据用户ID获取其会员及当日数据统计")
    @RequestMapping("/getTotalDataAndActivity")
    @ApiImplicitParam(name = "type", value = "引流活动类型", dataType = "String", required = true)
    public Object getTotalDataAndActivity(@ApiIgnore HttpServletRequest request, String type) {

        JSONObject object = memberStatisticService.getTotalDataAndActivity((String) request.getAttribute("sysid"), request.getParameterMap().get("type").toString());
        if (object == null && object.size() == 0) {
            return new Result(204, false, "无数据", null);
        }
        return new Result(200, true, "查询成功", object);
    }

    /**
     * 商家ID获取会员用户集合
     *
     * @param request
     * @param pageSize
     * @param startRow
     * @return
     */
    @ApiOperation(value = "会员管理--商家ID获取会员用户集合", tags = "会员管理--商家ID获取会员用户集合 - 分页")
    @RequestMapping("/getUseUserList")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索条件", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getUseUserList(@ApiIgnore HttpServletRequest request, int pageSize, int startRow, String search) {
        String phone = null;
        String name = null;
        if (StringUtils.isNotEmpty(search)) {
            if (StringUtils.isNumeric(search)) {
                phone = search;
            } else {
                name = search;
            }
        }
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("phone", phone);
        map.put("name", name);
        map.put("userId", request.getAttribute("sysid"));

        return new Result(200, true, "查询成功", memberStatisticService.getUserList(map));
    }

    /**
     * 活动列表
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "会员管理--活动列表", tags = " 获取已创建的优惠券 type 0：会员裂变；1：会员返利；2：店铺分销； - limit为获取数据条数")
    @RequestMapping("/getCouponList")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "引流活动类型", dataType = "int", required = true)})
    public Object getCouponList(@ApiIgnore HttpServletRequest request, int type) {
        JSONObject object = memberStatisticService.getActivity((String) request.getAttribute("sysid"), type);
        if (object == null && object.size() == 0) {
            return new Result(204, false, "无数据", null);
        }
        return new Result(200, true, "查询成功", object);
    }


}
