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

package com.tencent.bk.codecc.defect.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 告警提交的请求体
 *
 * @version V1.0
 * @date 2019/10/16
 */
@Data
@ApiModel("告警提交的请求体")
@AllArgsConstructor
@NoArgsConstructor
public class CommitDefectVO {

    @ApiModelProperty(value = "任务ID", required = true)
    private long taskId;

    @ApiModelProperty(value = "流名称", required = true)
    private String streamName;

    @ApiModelProperty(value = "工具名称", required = true)
    private String toolName;

    @ApiModelProperty(value = "构建Id", required = true)
    private String buildId;

    @ApiModelProperty(value = "触发来源,用于保存任务手动触发的触发人")
    private String triggerFrom;

    @ApiModelProperty(value = "备注信息")
    private String message;

    @ApiModelProperty(value = "创建来源")
    private String createFrom;

    @ApiModelProperty(value = "重新提交次数")
    private Integer recommitTimes;

    @ApiModelProperty("告警文件大小，用于MQ投递决策")
    private Long defectFileSize;

    @ApiModelProperty("是否重新分配")
    private boolean isReallocate;

}
