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

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 批量告警处理的返回对象
 *
 * @version V1.0
 * @date 2019/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量告警处理的返回对象")
public class BatchDefectProcessRspVO {

    /**
     * 操作类型
     */
    private String bizType;

    /**
     * 影响数量
     */
    private Long count;

    /**
     * 失败的数量
     */
    private Long failCount;
}
