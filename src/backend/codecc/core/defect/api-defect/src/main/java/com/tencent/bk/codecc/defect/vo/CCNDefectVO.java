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

import com.tencent.devops.common.api.CommonVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 圈复杂度缺陷视图
 *
 * @version V1.0
 * @date 2019/5/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("圈复杂度缺陷视图")
public class CCNDefectVO extends CommonVO {

    @ApiModelProperty("告警ID")
    private String id;

    /**
     * 签名，用于唯一认证
     */
    @ApiModelProperty("签名")
    private String funcSignature;
    /**
     * 任务id
     */
    @ApiModelProperty("任务id")
    private long taskId;

    /**
     * 方法名
     */
    @ApiModelProperty("方法名")
    private String functionName;

    /**
     * 方法的完整名称
     */
    @ApiModelProperty("方法的完整名称")
    private String longName;

    /**
     * 圈复杂度
     */
    @ApiModelProperty("圈复杂度")
    private int ccn;

    /**
     * 方法最后更新时间
     */
    @ApiModelProperty("方法最后更新时间")
    private Long latestDateTime;

    /**
     * 方法最后更新作者
     */
    @ApiModelProperty("方法最后更新作者")
    private String author;

    /**
     * 方法开始行号
     */
    @ApiModelProperty("方法开始行号")
    private Integer startLines;

    /**
     * 方法结束行号
     */
    @ApiModelProperty("方法结束行号")
    private Integer endLines;

    /**
     * 方法总行数
     */
    @ApiModelProperty("方法总行数")
    private Integer totalLines;

    /**
     * 包含圈复杂度计算节点的行号
     */
    @ApiModelProperty("包含圈复杂度计算节点的行号")
    private String conditionLines;

    /**
     * 告警方法的状态：new，closed，excluded，ignore
     */
    @ApiModelProperty(value = "告警方法的状态：new，closed，excluded，ignore", allowableValues = "{new, closed, excluded, ignore}")
    private int status;

    /**
     * 风险系数，极高-1, 高-2，中-4，低-8
     * 该参数不入库，因为风险系数是可配置的
     */
    @ApiModelProperty(value = "风险系数，极高-1, 高-2，中-4，低-8", allowableValues = "{1,2,4,8}")
    private int riskFactor;

    /**
     * 告警创建时间
     */
    @ApiModelProperty(value = "告警创建时间")
    private Long createTime;

    /**
     * 告警修复时间
     */
    @ApiModelProperty(value = "告警修复时间")
    private Long fixedTime;

    /**
     * 告警忽略时间
     */
    @ApiModelProperty(value = "告警忽略时间")
    private Long ignoreTime;

    /**
     * 告警忽略原因类型
     */
    @ApiModelProperty("告警忽略原因类型")
    private Integer ignoreReasonType;

    /**
     * 告警忽略原因
     */
    @ApiModelProperty("告警忽略原因")
    private String ignoreReason;

    /**
     * 告警忽略操作人
     */
    @ApiModelProperty("告警忽略操作人")
    private String ignoreAuthor;

    /**
     * 告警屏蔽时间
     */
    @ApiModelProperty(value = "告警屏蔽时间")
    private Long excludeTime;

    /**
     * 告警是否被标记为已修改的标志，checkbox for developer, 0 is normal, 1 is tag, 2 is prompt
     */
    @ApiModelProperty(value = "告警是否被标记为已修改的标志")
    private Integer mark;

    @ApiModelProperty(value = "告警被标记为已修改的时间")
    private Long markTime;

    @ApiModelProperty(value = "标记了，但是再次扫描没有修复")
    private Boolean markButNoFixed;

    /**
     * 文件相对路径
     */
    @ApiModelProperty(value = "文件相对路径")
    private String relPath;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 代码仓库地址
     */
    @ApiModelProperty(value = "代码仓库地址")
    private String url;

    /**
     * 仓库id
     */
    @ApiModelProperty(value = "仓库id")
    private String repoId;

    /**
     * 文件版本号
     */
    @ApiModelProperty(value = "文件版本号")
    private String revision;

    /**
     * 分支名
     */
    @ApiModelProperty(value = "分支名")
    private String branch;

    /**
     * Git子模块
     */
    @ApiModelProperty(value = "Git子模块")
    private String subModule;

    /**
     * 发现该告警的最近分析版本号，项目工具每次分析都有一个版本，用于区分一个方法是哪个版本扫描出来的，根据版本号来判断是否修复
     */
    @ApiModelProperty(value = "发现该告警的最近分析版本号，项目工具每次分析都有一个版本，用于区分一个方法是哪个版本扫描出来的，根据版本号来判断是否修复")
    private String analysisVersion;


    /**
     * 告警创建构件号
     */
    @ApiModelProperty("告警创建构建号")
    private String createBuildNumber;

    /**
     * 修复时的构建号
     */
    @ApiModelProperty("告警修复构建号")
    private String fixedBuildNumber;

    /**
     * 告警评论
     */
    @ApiModelProperty(value = "告警评论")
    private CodeCommentVO codeComment;

    /**
     * 是否注释忽略
     */
    @ApiModelProperty("是否注释忽略")
    private Boolean ignoreCommentDefect;

    @ApiModelProperty("所属任务名称")
    private String taskNameCn;

    @ApiModelProperty("提交人")
    private String commitAuthor;
}
