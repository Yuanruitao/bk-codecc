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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 持续集成项目组织架构视图
 *
 * @version V1.0
 * @date 2020/4/13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("持续集成项目组织架构视图")
public class DevopsProjectOrgVO {
    @ApiModelProperty("事业群ID")
    private Integer bgId;

    @ApiModelProperty("业务线ID")
    private Integer businessLineId;

    @ApiModelProperty("部门ID")
    private Integer deptId;

    @ApiModelProperty("中心ID")
    private Integer centerId;

    @ApiModelProperty("项目组ID")
    private Integer groupId;
}
