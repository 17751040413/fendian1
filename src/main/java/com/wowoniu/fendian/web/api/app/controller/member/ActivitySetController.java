package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuany
 * @date 2020-07-08
 */
@Api(value = "活动设置管理控制层", tags = "引流活动设置接口")
@RestController
@RequestMapping("/activitySet")
public class ActivitySetController {

    @Autowired
    private ActivitySetService activitySetService;

    /************************************************* 裂变 - START *********************************************************/

    @PostMapping("/getFission")
    @ApiOperation("商家ID获取裂变及详情")
    public Object getFission(@ApiIgnore HttpServletRequest request) {

        JSONObject jsonObject = activitySetService.getFissionAndDetail((String) request.getAttribute("sysid"));
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("裂变活动设置(新增/修改)")
    @PostMapping("/setFission")
    public Object setFission(@RequestBody FissionSet fissionSet, @ApiIgnore HttpServletRequest request) {

        fissionSet.setUserId((String) request.getAttribute("sysid"));
        boolean result = activitySetService.addOrUpdateFission(fissionSet);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("裂变详情设置(新增/修改)")
    @PostMapping("/setFissionDetail")
    public Object setFissionDetail(@RequestBody FissionSetDetail fissionSetDetail) {

        boolean result = activitySetService.addOrUpdateFissionDetail(fissionSetDetail);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除裂变详情")
    @PostMapping("/deleteFissionDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "裂变详情ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "fissionId", value = "裂变ID", dataType = "String", required = true)})
    public Object deleteFissionDetail(String id, String fissionId) {

        boolean result = activitySetService.deleteFissionDetail(id, fissionId);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }

    /************************************************* 裂变 - END *********************************************************/

    /************************************************* 返利 - START *********************************************************/
    @PostMapping("/getRebate")
    @ApiOperation("商家ID获取返利及详情")
    public Object getRebate(@ApiIgnore HttpServletRequest request) {

        JSONObject jsonObject = activitySetService.getRebateAndDetail((String) request.getAttribute("sysid"));
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("返利活动设置(新增/修改)")
    @PostMapping("/setRebate")
    public Object setRebate(@RequestBody RebateSet rebateSet, @ApiIgnore HttpServletRequest request) {

        rebateSet.setUserId((String) request.getAttribute("sysid"));
        boolean result = activitySetService.addOrUpdateRebate(rebateSet);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("返利详情设置(新增/修改)")
    @PostMapping("/setRebateDetail")
    public Object setRebateDetail(@RequestBody RebateSetDetail rebateSetDetail) {

        boolean result = activitySetService.addOrUpdateRebateSetDetail(rebateSetDetail);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除返利详情")
    @PostMapping("/deleteRebateDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "返利详情ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "rebateId", value = "返利ID", dataType = "String", required = true)})
    public Object deleteRebateDetail(String id, String rebateId) {

        boolean result = activitySetService.deleteRebateDetail(id, rebateId);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }
    /************************************************* 返利 - END *********************************************************/

    /************************************************* 分销 - START *********************************************************/
    @PostMapping("/getDistribution")
    @ApiOperation("商家ID获取返利及详情")
    public Object getDistribution(@ApiIgnore HttpServletRequest request) {

        JSONObject jsonObject = activitySetService.getDistributionAndDetail((String) request.getAttribute("sysid"));
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("分销活动设置(新增/修改)")
    @PostMapping("/setRebate")
    public Object setDistribution(@RequestBody DistributionSet distributionSet, @ApiIgnore HttpServletRequest request) {

        distributionSet.setUserId((String) request.getAttribute("sysid"));
        boolean result = activitySetService.addOrUpdateDistribution(distributionSet);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("分销优惠券设置(新增/修改)")
    @PostMapping("/setDistributionCoupon")
    public Object setDistributionCoupon(@RequestBody DistributionCoupon distributionCoupon) {

        boolean result = activitySetService.addOrUpdateDistributionCoupon(distributionCoupon);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除分销优惠券")
    @PostMapping("/deleteDistributionCoupon")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "返利详情ID", dataType = "String", required = true)})
    public Object deleteDistributionCoupon(String id) {

        boolean result = activitySetService.deleteDistributionCoupon(id);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }
    /************************************************* 分销 - END *********************************************************/

    /************************************************* 商城 - END *********************************************************/

    /************************************************* 商城 - END *********************************************************/
}
