package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.FissionSet;
import com.wowoniu.fendian.model.FissionSetDetail;
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
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID", dataType = "String", required = true)})
    public Object getFission(String userId) {

        JSONObject jsonObject = activitySetService.getFissionAndDetail(userId);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("裂变活动设置(新增/修改)")
    @PostMapping("/setFission")
    public Object setFission(@RequestBody FissionSet fissionSet) {

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

}
