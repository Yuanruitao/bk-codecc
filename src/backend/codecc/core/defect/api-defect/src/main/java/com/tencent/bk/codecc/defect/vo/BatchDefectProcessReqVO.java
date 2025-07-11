/*
 * Tencent is pleased to support the open source community by making BlueKing available.
 * Copyright (C) 2017-2018 Tencent. All rights reserved.
 * Licensed under the MIT License (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://opensource.org/licenses/MIT
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.bk.codecc.defect.vo;

import com.tencent.bk.codecc.defect.vo.common.DefectQueryReqVO;
import com.tencent.devops.common.api.codecc.util.JsonUtil;
import com.tencent.devops.common.api.exception.CodeCCException;
import com.tencent.devops.common.constant.CommonMessageCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 批量告警处理的请求对象
 *
 * @version V1.0
 * @date 2019/10/31
 */
@Data
@ApiModel("批量告警处理的请求对象")
@Slf4j
public class BatchDefectProcessReqVO {

    @ApiModelProperty("操作的用户")
    private String userName;

    @ApiModelProperty("任务ID")
    private Long taskId;

    @ApiModelProperty("任务ID列表")
    private List<Long> taskIdList;

    @ApiModelProperty("项目ID")
    private String projectId;

    @ApiModelProperty("工具名称")
    private List<String> toolNameList;

    @ApiModelProperty("工具名称")
    private List<String> dimensionList;

    @ApiModelProperty("需要处理的SCA维度")
    private String scaDimension;

    @ApiModelProperty("业务类型：忽略IgnoreDefect、分配AssignDefect、标志修改MarkDefect")
    private String bizType;

    @ApiModelProperty("是否全选的标志，Y表示全选，N或者空表示非全选")
    private String isSelectAll;

    @ApiModelProperty("告警缺陷列表")
    private Set<String> defectKeySet;

    @ApiModelProperty("文件告警列表")
    private List<QueryFileDefectVO> fileDefects;

    @ApiModelProperty("告警查询条件json")
    private String queryDefectCondition;

    @ApiModelProperty("源告警处理人")
    private Set<String> sourceAuthor;

    @ApiModelProperty("分配给新的处理人")
    private LinkedHashSet<String> newAuthor;

    @ApiModelProperty("忽略告警原因类型")
    private int ignoreReasonType;

    @ApiModelProperty("忽略告警具体原因")
    private String ignoreReason;

    @ApiModelProperty("忽略告警的作者")
    private String ignoreAuthor;

    @ApiModelProperty("标志修改，0表示取消标志，1表示标志修改")
    private Integer markFlag;

    @ApiModelProperty(value = "数据迁移是否成功", required = false)
    private Boolean dataMigrationSuccessful;

    @ApiModelProperty("是否为回复忽略再标记")
    private Boolean revertAndMark;

    @ApiModelProperty("是否强制提交告警到tapd")
    private Boolean forceSubmit;

    public String getToolName() {
        if (getToolNameList() == null || getToolNameList().isEmpty()) {
            return null;
        }
        return String.join(",", getToolNameList());
    }

    public String getDimension() {
        if (getDimensionList() == null || getDimensionList().isEmpty()) {
            return null;
        }
        return String.join(",", getDimensionList());
    }

    /**
     * 获取 taskIdList 的第一个 task id, 用于审计数据上报, <b>谨慎删除</b>
     */
    public Long getFirstTaskId() {
        if (taskIdList == null || taskIdList.isEmpty()) {
            return -1L;
        }

        return taskIdList.get(0);
    }

    /**
     * 获取 defectKeySet 的第一个 defect key, 用于审计数据上报, <b>谨慎删除</b>
     */
    public String getFirstDefectKey() {
        if (defectKeySet == null || defectKeySet.isEmpty()) {
            return "";
        }

        Optional<String> result = defectKeySet.stream().findFirst();
        return result.orElse("");
    }



    public DefectQueryReqVO convertDefectQueryReqVO() {
        String queryDefectCondition = getQueryDefectCondition();
        if (queryDefectCondition == null || queryDefectCondition.isEmpty()) {
            return null;
        }
        DefectQueryReqVO queryCondObj = JsonUtil.INSTANCE.to(queryDefectCondition, DefectQueryReqVO.class);
        if (queryCondObj == null) {
            log.error("defect batch op, query obj deserialize fail, json: {}", queryDefectCondition);
            throw new CodeCCException(CommonMessageCode.PARAMETER_IS_INVALID);
        }
        queryCondObj.setRevertAndMark(getRevertAndMark());
        return queryCondObj;
    }
}
