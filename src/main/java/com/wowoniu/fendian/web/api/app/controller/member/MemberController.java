package com.wowoniu.fendian.web.api.app.controller.member;

import com.wowoniu.fendian.service.MemberStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理控制层
 *
 * @author yuany
 * @date 2020-06-22
 */
@Api(value = "会员管理控制层", tags = "会员统计管理设置接口")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberStatisticService memberStatisticService;

    /**
     * 根据用户ID获取其会员及当日数据统计，及活动列表
     *
     * @param userId 用户ID
     * @return
     */
    @ApiOperation(value = "会员统计数据,及活动列表", tags = "根据用户ID获取其会员及当日数据统计")
    @RequestMapping("/getTotalDataAndActivity")
    public Object getTotalDataAndActivity(@ApiParam(name = "userId", value = "登录用户ID", required = true) String userId,
                                     @ApiParam(name = "type", value = "引流活动类型", required = true) String type) {
        return memberStatisticService.getTotalDataAndActivity(userId,type);
    }

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param userId 用户ID
     * @param limit  数据量
     * @return
     */
    @ApiOperation(value = "父级用户ID获取会员用户集合", tags = "limit为获取数据条数")
    @RequestMapping("/getMemberList")
    public Object getMemberList(@ApiParam(name = "userId", value = "登录用户ID", required = true) String userId,
                                @ApiParam(name = "limit", value = "数据量", required = true) Integer limit) {

        return memberStatisticService.getMemberList(userId, limit);
    }
}
