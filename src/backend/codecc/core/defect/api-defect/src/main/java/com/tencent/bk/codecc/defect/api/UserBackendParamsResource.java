/*
 * Tencent is pleased to support the open source community by making BK-CODECC 蓝鲸代码检查平台 available.
 *
 * Copyright (C) 2019 Tencent.  All rights reserved.
 *
 * BK-CODECC 蓝鲸代码检查平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.bk.codecc.defect.api;

import com.tencent.bk.codecc.defect.vo.common.BackendParamsVO;
import com.tencent.devops.common.api.pojo.codecc.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * 后端配置参数接口
 *
 * @version V1.0
 * @date 2019/5/29
 */
@Api(tags = {"BACKEND_PARAMS"}, description = " 后端配置参数接口")
@Path("/user/backendParams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserBackendParamsResource {

    @ApiOperation("查询后端配置参数")
    @Path("/")
    @GET
    Result<BackendParamsVO> getParams();


    @ApiOperation("告警提交MQ触发-大项目")
    @Path("/pubMsgToCommitDefect/{mode}")
    @POST
    Result<Boolean> pubMsgToCommitDefect(
            @PathParam("mode")
            String mode,
            String jsonBody
    );

    @ApiOperation("数据迁移模式开关")
    @Path("/dataMigration/commonToLint/switch")
    @POST
    Result<Boolean> commonToLintDataMigrationSwitch(
            @ApiParam(value = "三种模式: single/batch/off", required = true)
            @QueryParam("mode")
            String mode
    );

    @ApiOperation("触发批量数据迁移")
    @Path("/dataMigration/trigger/batchCommonToLint")
    @POST
    Result<Boolean> batchCommonToLintDataMigration();

    @ApiOperation("提单屏蔽")
    @Path("/addCommitDefectBlockList")
    @POST
    Result<String> addCommitDefectBlockList(List<Long> taskIds);

    @ApiOperation("清空提单屏蔽列表")
    @Path("/clearCommitDefectBlockList")
    @POST
    Result<Boolean> clearCommitDefectBlockList(List<Long> taskIds);

    @ApiOperation("降冷指定任务")
    @Path("/coolDown/{taskId}")
    @POST
    Result<Boolean> coolDown(@PathParam("taskId") Long taskId);

    @ApiOperation("加热指定任务")
    @Path("/warmUp/{taskId}")
    @POST
    Result<Boolean> warmUp(@PathParam("taskId") Long taskId);

    @ApiOperation("触发冷热分离任务下发")
    @Path("/hotColdDataSeparationTrigger")
    @POST
    Result<Boolean> hotColdDataSeparationTrigger();
}
