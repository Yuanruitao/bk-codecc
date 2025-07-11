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

package com.tencent.bk.codecc.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 任务清单查询请求视图
 *
 * @date 2020/2/11
 * @version V1.0
 */
@Data
@ApiModel("任务清单查询请求视图")
public class TaskListReqVO {
    @ApiModelProperty("任务状态")
    private TaskListStatus taskStatus;

    @ApiModelProperty("任务名: bs_pipeline -- 流水线;  bs_codecc;服务创建")
    private String taskSource;

    @ApiModelProperty(value = "第几页")
    private int page = 0;

    @ApiModelProperty(value = "每页多少条")
    private int pageSize = 10;

    @ApiModelProperty(value = "是否分页")
    private Boolean pageable;

    @ApiModelProperty(value = "true, 展示已停用任务; false, 展示启用任务")
    private Boolean showDisabledTask;

    @ApiModelProperty(value = "选择展示的任务类型. 1, 启用任务; 2, 停用任务; 3, 测试任务")
    private Integer showTaskType;

    @ApiModelProperty("任务状态集合")
    private List<TaskListStatus> taskStatusList;
}
