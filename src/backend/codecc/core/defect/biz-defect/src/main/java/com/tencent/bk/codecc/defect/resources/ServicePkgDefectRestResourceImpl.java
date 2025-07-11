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

package com.tencent.bk.codecc.defect.resources;

import com.tencent.bk.codecc.defect.api.ServicePkgDefectRestResource;
import com.tencent.bk.codecc.defect.service.ICLOCQueryCodeLineService;
import com.tencent.bk.codecc.defect.service.IQueryWarningBizService;
import com.tencent.bk.codecc.defect.vo.CLOCDefectQueryRspInfoVO;
import com.tencent.bk.codecc.defect.vo.ToolClocRspVO;
import com.tencent.devops.common.api.pojo.codecc.Result;
import com.tencent.devops.common.service.BizServiceFactory;
import com.tencent.devops.common.web.RestResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 规则相关接口实现类
 *
 * @version V1.0
 * @date 2019/11/15
 */
@Slf4j
@RestResource
public class ServicePkgDefectRestResourceImpl implements ServicePkgDefectRestResource {

    @Autowired
    private BizServiceFactory<IQueryWarningBizService> fileAndDefectQueryFactory;

    @Autowired
    private ICLOCQueryCodeLineService iclocQueryCodeLineService;

    @Override
    public Result<ToolClocRspVO> queryCodeLine(Long taskId, String toolName) {
        return new Result<>(iclocQueryCodeLineService.getCodeLineInfo(taskId, toolName));
    }

    @Override
    public Result<CLOCDefectQueryRspInfoVO> queryCodeLineByTaskIdAndLanguge(
            Long taskId,
            String toolName,
            String language) {
        return new Result<>(iclocQueryCodeLineService.generateSpecificLanguage(taskId, toolName, language));
    }
}
