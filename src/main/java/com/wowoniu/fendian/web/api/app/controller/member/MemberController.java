package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.service.MemberStatisticService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

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
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "引流活动类型", dataType = "String", required = true)})
    public Object getTotalDataAndActivity(@ApiIgnore HttpServletRequest request, String type) {
        JSONObject object = memberStatisticService.getTotalDataAndActivity((String) request.getAttribute("sysid"), type);
        if (object == null && object.size() == 0) {
            return new Result(204, false, "无数据", null);
        }
        return new Result(200, true, "查询成功", object);
    }

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param limit 数据量
     * @return
     */
    @ApiOperation(value = "会员管理--父级用户ID获取会员用户集合", tags = "limit为获取数据条数")
    @RequestMapping("/getUseUserList")
    @ApiImplicitParams({@ApiImplicitParam(name = "limit", value = "数据量", dataType = "Integer", required = true)})
    public Object getUseUserList(@ApiIgnore HttpServletRequest request, Integer limit) {
        Object object = memberStatisticService.getUseUserList((String) request.getAttribute("sysid"), limit);
        if (object == null) {
            return new Result(204, false, "无数据", null);
        }
        return new Result(200, true, "查询成功", object);
    }

    /**
     * 活动列表
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "会员管理--活动列表", tags = "limit为获取数据条数")
    @RequestMapping("/getMemberList")
    public Object getMemberList(@ApiIgnore HttpServletRequest request) {
        JSONObject object = memberStatisticService.getActivity((String) request.getAttribute("sysid"));
        if (object == null && object.size() == 0) {
            return new Result(204, false, "无数据", null);
        }
        return new Result(200, true, "查询成功", object);
    }


}
