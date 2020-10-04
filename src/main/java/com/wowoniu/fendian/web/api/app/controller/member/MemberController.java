package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.Member;
import com.wowoniu.fendian.service.MemberStatisticService;
import com.wowoniu.fendian.utils.Result;
import com.wowoniu.fendian.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员管理控制层
 *
 * @author
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

        JSONObject object = memberStatisticService.getTotalDataAndActivity((String) request.getAttribute("sysid"), type);
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
    @ApiOperation(value = "会员管理 会员名单 --商家ID获取会员用户集合", tags = "会员管理--商家ID获取会员用户集合 - 分页")
    @RequestMapping("/getUseUserList")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索条件", dataType = "String", required = true),
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
    @ApiOperation(value = "会员管理--活动列表", tags = " 获取已创建的优惠券 type 0：会员裂变；1：会员返利；2：店铺分销； ")
    @RequestMapping("/getCouponList")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true),
            @ApiImplicitParam(name = "type", value = "引流活动类型 type 0：会员裂变；1：会员返利；2：店铺分销", dataType = "int", required = true)})
    public Object getCouponList(@ApiIgnore HttpServletRequest request, int pageSize, int startRow, int type) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("type", type + "");
        map.put("userId", request.getAttribute("sysid"));
        return new Result(200, true, "查询成功", memberStatisticService.getActivity(map));
    }

    @ApiOperation(value = "1-6-2-8 会员余额变动记录")
    @RequestMapping("/getMemberPrice")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true),
            @ApiImplicitParam(name = "id", value = "会员列表中 会员ID", dataType = "String", required = true)})
    public Object getMemberPrice(int pageSize, int startRow, String id) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("id", id);
        return new Result(200, true, "查询成功", memberStatisticService.getMemberPrice(map));
    }

    @ApiOperation(value = "1-6-2-8 所有会员余额变动记录")
    @RequestMapping("/getAllMemberPrice")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "类型（0：返现到账，1：到店消费，2：充值,，3.核销）", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getAllMemberPrice(@ApiIgnore HttpServletRequest request, int pageSize, int startRow,String type) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("type",type);
        map.put("userId", request.getAttribute("sysid"));
        return new Result(200, true, "查询成功", memberStatisticService.getAllMemberPrice(map));
    }

    @ApiOperation(value = "1-6-2-9 1-6-2-7 获取会员信息")
    @RequestMapping("/getMember")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "会员列表中 会员ID", dataType = "String", required = true)})
    public Object getMember(String id) {
        return new Result(200, true, "查询成功", memberStatisticService.getMember(id));
    }

    @ApiOperation(value = "1-6-2-9 获取会员等级列表")
    @RequestMapping("/getLevel")
    public Object getLevel(@ApiIgnore HttpServletRequest request) {
        List<Map<String, Object>> mapList = memberStatisticService.getLevel((String) request.getAttribute("sysid"));
        if (CollectionUtils.isEmpty(mapList)) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", mapList);
    }

    @ApiOperation(value = "1-6-2-9-修改会员信息")
    @RequestMapping("/updateMember")
    public Object updateMember(Member member) {
        memberStatisticService.updateMember(member);
        return new Result(200, true, "修改成功", null);
    }

    @ApiOperation(value = "1-6-2-7 获取记录 消费记录 /余额记录")
    @RequestMapping("/getMemberRecord")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true),
            @ApiImplicitParam(name = "id", value = "会员列表中 会员ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "date", value = "日期（年月日 格式例如 2020-09-28）", dataType = "String", required = true),
            @ApiImplicitParam(name = "type", value = "记录类型:0-消费记录 1-余额记录 ", dataType = "String", required = true)})
    public Object getMemberRecord(int pageSize, int startRow, String id, String date, String type) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("startRow", startRow);
        map.put("date", date);
        map.put("type", type);
        map.put("id", id);
        return new Result(200, true, "获取成功", memberStatisticService.getMemberRecord(map));
    }

    @ApiOperation(value = "1-6-2-7 获取记录 邀请")
    @RequestMapping("/getMemberInviter")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true),
            @ApiImplicitParam(name = "date", value = "日期（年月日 格式例如 2020-09-28）", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "会员列表中 会员ID", dataType = "String", required = true)})
    public Object getMemberInviter(int pageSize, int startRow, String date, String id) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("date", date);
        map.put("id", id);
        return new Result(200, true, "获取成功", memberStatisticService.getMemberInviter(map));
    }


}
