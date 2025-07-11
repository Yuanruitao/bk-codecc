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
 
package com.tencent.bk.codecc.task.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业微信通知数据模型
 * 
 * @date 2019/12/3
 * @version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RtxNotifyModel 
{
    /**
     * 代码扫描任务id
     */
    private Long taskId;

    /**
     * 通知内容
     */
    private Boolean success;

    private String buildId;
}
